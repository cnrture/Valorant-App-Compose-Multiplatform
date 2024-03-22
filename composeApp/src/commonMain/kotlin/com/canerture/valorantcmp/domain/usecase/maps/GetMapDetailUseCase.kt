package com.canerture.valorantcmp.domain.usecase.maps

import com.canerture.valorantcmp.domain.mapper.mapToMapUI
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetMapDetailUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(id: String): Result<MapUI> {
        return valorantRepository.getMapDetail(id).map {
            it.mapToMapUI()
        }
    }
}
