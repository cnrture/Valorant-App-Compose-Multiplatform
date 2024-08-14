package com.canerture.valorantcmp.presentation.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.maps.GetMapsUseCase
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiAction
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiEffect
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiState
import kotlinx.coroutines.launch

class MapsViewModel(
    private val getMapsUseCase: GetMapsUseCase
) : ViewModel(), MVI<UiState, UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnMapClick -> emitUiEffect(UiEffect.GoToMapDetail(uiAction.id))
            }
        }
    }

    fun getMaps() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getMapsUseCase().onSuccess {
                emitUiEffect(UiEffect.ShowError("it.message.orEmpty()"))
            }.onFailure {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }
}
