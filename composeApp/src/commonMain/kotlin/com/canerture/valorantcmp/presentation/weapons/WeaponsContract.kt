package com.canerture.valorantcmp.presentation.weapons

import com.canerture.valorantcmp.domain.model.WeaponUI

object WeaponsContract {
    data class UiState(
        val isLoading: Boolean = false,
        val weapons: List<WeaponUI> = emptyList(),
    )

    sealed class UiAction {
        data class OnWeaponClick(val id: String) : UiAction()
    }

    sealed class UiEffect {
        data class GoToWeaponDetail(val id: String) : UiEffect()
        data class ShowError(val message: String) : UiEffect()
    }
}