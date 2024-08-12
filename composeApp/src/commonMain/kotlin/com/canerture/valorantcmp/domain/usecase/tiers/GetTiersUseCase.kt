package com.canerture.valorantcmp.domain.usecase.tiers

import com.canerture.valorantcmp.domain.mapper.mapToTierUI
import com.canerture.valorantcmp.domain.model.TierUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetTiersUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(): Result<List<TierUI>> {
        return valorantRepository.getTiers().map {
            it?.last()?.tiers.mapToTierUI()
        }
    }
}
