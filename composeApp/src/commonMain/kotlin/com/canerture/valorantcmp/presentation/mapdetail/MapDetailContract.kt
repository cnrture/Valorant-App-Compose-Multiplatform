package com.canerture.valorantcmp.presentation.mapdetail

import com.canerture.valorantcmp.domain.model.MapUI

object MapDetailContract {
    data class UiState(
        val isLoading: Boolean = false,
        val map: MapUI? = null,
    )

    sealed class UiAction {
        data object OnBackClick : UiAction()
    }

    sealed class UiEffect {
        data object GoToBack : UiEffect()
        data class ShowError(val message: String) : UiEffect()
    }
}