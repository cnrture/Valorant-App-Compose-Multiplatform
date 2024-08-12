package com.canerture.valorantcmp.presentation.mapdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.domain.usecase.maps.GetMapDetailUseCase
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailContract.UiEffect
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapDetailViewModel(
    private val getMapDetailUseCase: GetMapDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: MapDetailContract.UiAction) = viewModelScope.launch {
        when (uiAction) {
            is MapDetailContract.UiAction.OnBackClick -> emitUiEffect(UiEffect.GoToBack)
        }
    }

    fun getMapDetail(id: String) = viewModelScope.launch {
        getMapDetailUseCase(id).onSuccess {
            updateUiState { copy(map = it) }
        }.onFailure {
            emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}