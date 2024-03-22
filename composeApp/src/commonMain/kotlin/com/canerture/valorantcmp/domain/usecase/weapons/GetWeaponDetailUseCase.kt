package com.canerture.valorantcmp.domain.usecase.weapons

import com.canerture.valorantcmp.domain.mapper.mapToWeaponUI
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetWeaponDetailUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(id: String): Result<WeaponUI> {
        return valorantRepository.getWeaponDetail(id).map {
            it.mapToWeaponUI()
        }
    }
}
