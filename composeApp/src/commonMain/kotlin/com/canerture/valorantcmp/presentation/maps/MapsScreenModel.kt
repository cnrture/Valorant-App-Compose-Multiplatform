package com.canerture.valorantcmp.presentation.maps

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.domain.usecase.maps.GetMapsUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapsScreenModel(
    private val getMapsUseCase: GetMapsUseCase
) : StateScreenModel<MapsState>(MapsState.Loading) {

    fun getMaps() = screenModelScope.launch {
        getMapsUseCase().onSuccess {
            if (it.isNotEmpty()) {
                setState { MapsState.Content(it) }
            } else {
                setState { MapsState.Empty }
            }
        }.onFailure {
            setState { MapsState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: MapsState.() -> MapsState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface MapsState {
    data object Loading : MapsState
    data object Empty : MapsState
    data class Content(val maps: List<MapUI>) : MapsState
    data class ShowError(val message: String?) : MapsState
}
