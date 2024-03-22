package com.canerture.valorantcmp.presentation.mapdetail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.domain.usecase.maps.GetMapDetailUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapDetailScreenModel(
    private val getMapDetailUseCase: GetMapDetailUseCase,
) : StateScreenModel<MapDetailState>(MapDetailState.Loading) {

    fun getMapDetail(id: String) = screenModelScope.launch {
        getMapDetailUseCase(id).onSuccess {
            setState { MapDetailState.Content(it) }
        }.onFailure {
            setState { MapDetailState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: MapDetailState.() -> MapDetailState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface MapDetailState {
    data object Loading : MapDetailState
    data class Content(val map: MapUI) : MapDetailState
    data class ShowError(val message: String?) : MapDetailState
}
