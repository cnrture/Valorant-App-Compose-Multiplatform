package com.canerture.valorantcmp.domain.usecase.agents

import com.canerture.valorantcmp.domain.mapper.mapToAgentRoleUI
import com.canerture.valorantcmp.domain.model.AgentGroupUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetAgentsUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(): Result<List<AgentGroupUI>> {
        return valorantRepository.getAgents().map {
            it.mapToAgentRoleUI()
        }
    }
}
