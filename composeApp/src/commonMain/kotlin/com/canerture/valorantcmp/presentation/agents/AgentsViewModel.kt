package com.canerture.valorantcmp.presentation.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentsUseCase
import com.canerture.valorantcmp.presentation.agents.AgentsContract.UiAction
import com.canerture.valorantcmp.presentation.agents.AgentsContract.UiEffect
import com.canerture.valorantcmp.presentation.agents.AgentsContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentsViewModel(
    private val getAgentsUseCase: GetAgentsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) = viewModelScope.launch {
        when (uiAction) {
            is UiAction.OnAgentClick -> emitUiEffect(UiEffect.GoToAgentDetail(uiAction.id))
        }
    }

    fun getAgents() {
        viewModelScope.launch {
            getAgentsUseCase().onSuccess {
                updateUiState { copy(agents = it) }
            }.onFailure {
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
