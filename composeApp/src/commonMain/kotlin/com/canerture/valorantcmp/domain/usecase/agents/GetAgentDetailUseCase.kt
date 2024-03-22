package com.canerture.valorantcmp.domain.usecase.agents

import com.canerture.valorantcmp.domain.mapper.mapToAgentUI
import com.canerture.valorantcmp.domain.model.AgentUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetAgentDetailUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(id: String): Result<AgentUI> {
        return valorantRepository.getAgentDetail(id).map {
            it.mapToAgentUI()
        }
    }
}
