package com.canerture.valorantcmp.presentation.agents

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.AgentGroupUI
import com.canerture.valorantcmp.domain.usecase.agents.GetAgentsUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AgentsScreenModel(
    private val getAgentsUseCase: GetAgentsUseCase
) : StateScreenModel<AgentsState>(AgentsState.Loading) {

    fun getAgents() = screenModelScope.launch {
        getAgentsUseCase().onSuccess {
            if (it.isEmpty()) {
                setState { AgentsState.Empty }
            } else {
                setState { AgentsState.Content(it) }
            }
        }.onFailure {
            setState { AgentsState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: AgentsState.() -> AgentsState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface AgentsState {
    data object Loading : AgentsState
    data object Empty : AgentsState
    data class Content(val agents: List<AgentGroupUI>) : AgentsState
    data class ShowError(val message: String?) : AgentsState
}
