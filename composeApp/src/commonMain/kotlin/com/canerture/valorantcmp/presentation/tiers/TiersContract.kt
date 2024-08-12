package com.canerture.valorantcmp.presentation.tiers

import com.canerture.valorantcmp.domain.model.TierUI

object TiersContract {
    data class UiState(
        val tiers: List<TierUI> = emptyList(),
    )

    sealed class UiEffect {
        data class ShowError(val message: String) : UiEffect()
    }
}