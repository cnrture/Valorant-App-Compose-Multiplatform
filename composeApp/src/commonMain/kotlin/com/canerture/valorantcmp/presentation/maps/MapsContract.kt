package com.canerture.valorantcmp.presentation.maps

import com.canerture.valorantcmp.domain.model.MapUI

object MapsContract {
    data class UiState(
        val isLoading: Boolean = false,
        val maps: List<MapUI> = emptyList(),
    )

    sealed class UiAction {
        data class OnMapClick(val id: String) : UiAction()
    }

    sealed class UiEffect {
        data class GoToMapDetail(val id: String) : UiEffect()
        data class ShowError(val message: String) : UiEffect()
    }
}