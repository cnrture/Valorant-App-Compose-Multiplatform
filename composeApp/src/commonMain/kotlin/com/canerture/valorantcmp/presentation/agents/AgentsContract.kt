package com.canerture.valorantcmp.presentation.agents

import com.canerture.valorantcmp.domain.model.AgentGroupUI

object AgentsContract {
    data class UiState(
        val isLoading: Boolean = false,
        val agents: List<AgentGroupUI> = emptyList(),
    )

    sealed class UiAction {
        data class OnAgentClick(val id: String) : UiAction()
    }

    sealed class UiEffect {
        data class GoToAgentDetail(val id: String) : UiEffect()
        data class ShowError(val message: String) : UiEffect()
    }
}