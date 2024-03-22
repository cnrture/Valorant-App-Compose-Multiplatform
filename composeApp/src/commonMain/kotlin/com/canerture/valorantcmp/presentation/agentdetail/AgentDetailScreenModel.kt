package com.canerture.valorantcmp.presentation.agentdetail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.AgentUI
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentDetailUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentDetailScreenModel(
    private val getAgentDetailUseCase: GetAgentDetailUseCase,
) : StateScreenModel<AgentDetailState>(AgentDetailState.Loading) {

    fun getAgentDetail(id: String) = screenModelScope.launch {
        getAgentDetailUseCase(id).onSuccess {
            setState { AgentDetailState.Content(it) }
        }.onFailure {
            setState { AgentDetailState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: AgentDetailState.() -> AgentDetailState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface AgentDetailState {
    data object Loading : AgentDetailState
    data class Content(val agent: AgentUI) : AgentDetailState
    data class ShowError(val message: String?) : AgentDetailState
}
