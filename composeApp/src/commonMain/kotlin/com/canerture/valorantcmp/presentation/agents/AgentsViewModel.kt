package com.canerture.valorantcmp.presentation.agents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentsUseCase
import com.canerture.valorantcmp.presentation.agents.AgentsContract.UiAction
import com.canerture.valorantcmp.presentation.agents.AgentsContract.UiEffect
import com.canerture.valorantcmp.presentation.agents.AgentsContract.UiState
import kotlinx.coroutines.launch

class AgentsViewModel(
    private val getAgentsUseCase: GetAgentsUseCase
) : ViewModel(), MVI<UiState, UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnAgentClick -> emitUiEffect(UiEffect.GoToAgentDetail(uiAction.id))
            }
        }
    }

    fun getAgents() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getAgentsUseCase().onSuccess {
                updateUiState { copy(isLoading = false, agents = it) }
            }.onFailure {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }
}
