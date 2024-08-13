package com.canerture.valorantcmp.presentation.mapdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.maps.GetMapDetailUseCase
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailContract.UiAction
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailContract.UiEffect
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailContract.UiState
import kotlinx.coroutines.launch

class MapDetailViewModel(
    private val getMapDetailUseCase: GetMapDetailUseCase,
) : ViewModel(), MVI<UiState, UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnBackClick -> emitUiEffect(UiEffect.GoToBack)
            }
        }
    }

    fun getMapDetail(id: String) {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getMapDetailUseCase(id).onSuccess {
                updateUiState { copy(isLoading = false, map = it) }
            }.onFailure {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }
}