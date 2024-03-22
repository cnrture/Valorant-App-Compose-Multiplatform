package com.canerture.valorantcmp.domain.usecase.maps

import com.canerture.valorantcmp.domain.mapper.mapToMapUI
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetMapsUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(): Result<List<MapUI>> {
        return valorantRepository.getMaps().map {
            it.mapToMapUI()
        }
    }
}
