package com.canerture.valorantcmp.presentation.agentdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentDetailUseCase
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailContract.UiAction
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailContract.UiEffect
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailContract.UiState
import kotlinx.coroutines.launch

class AgentDetailViewModel(
    private val getAgentDetailUseCase: GetAgentDetailUseCase,
) : ViewModel(), MVI<UiState, UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnBackClick -> emitUiEffect(UiEffect.GoToBack)
            }
        }
    }

    fun getAgentDetail(id: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getAgentDetailUseCase(id).onSuccess {
                updateUiState { copy(isLoading = false, agent = it) }
            }.onFailure {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }
}
