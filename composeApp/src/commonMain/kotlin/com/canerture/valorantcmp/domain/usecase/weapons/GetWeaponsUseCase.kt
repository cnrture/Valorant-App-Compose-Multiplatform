package com.canerture.valorantcmp.domain.usecase.weapons

import com.canerture.valorantcmp.domain.mapper.mapToWeaponUI
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.domain.repository.ValorantRepository

class GetWeaponsUseCase(
    private val valorantRepository: ValorantRepository
) {
    suspend operator fun invoke(): Result<List<WeaponUI>> {
        return valorantRepository.getWeapons().map {
            it.mapToWeaponUI()
        }
    }
}
