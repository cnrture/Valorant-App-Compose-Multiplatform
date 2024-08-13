package com.canerture.valorantcmp.presentation.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.maps.GetMapsUseCase
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiEffect
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiState
import kotlinx.coroutines.launch

class MapsViewModel(
    private val getMapsUseCase: GetMapsUseCase
) : ViewModel(), MVI<UiState, MapsContract.UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: MapsContract.UiAction) {
        when (uiAction) {
            is MapsContract.UiAction.OnMapClick -> getMaps()
        }
    }

    fun getMaps() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        getMapsUseCase().onSuccess {
            updateUiState { copy(isLoading = false, maps = it) }
        }.onFailure {
            updateUiState { copy(isLoading = false) }
            emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
        }
    }
}