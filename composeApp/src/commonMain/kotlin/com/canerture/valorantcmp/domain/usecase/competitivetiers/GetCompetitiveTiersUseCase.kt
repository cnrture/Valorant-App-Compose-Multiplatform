package com.canerture.valorantcmp.domain.usecase.competitivetiers

import com.canerture.valorantcmp.domain.mapper.mapToTierUI
import com.canerture.valorantcmp.domain.model.TierUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetCompetitiveTiersUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(): Result<List<TierUI>> {
        return valorantRepository.getCompetitiveTiers().map {
            it?.last()?.tiers.mapToTierUI()
        }
    }
}
