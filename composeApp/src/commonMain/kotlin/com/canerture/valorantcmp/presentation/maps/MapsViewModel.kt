package com.canerture.valorantcmp.presentation.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.domain.usecase.maps.GetMapsUseCase
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiEffect
import com.canerture.valorantcmp.presentation.maps.MapsContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MapsViewModel(
    private val getMapsUseCase: GetMapsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: MapsContract.UiAction) = viewModelScope.launch {
        when (uiAction) {
            is MapsContract.UiAction.OnMapClick -> emitUiEffect(UiEffect.GoToMapDetail(uiAction.id))
        }
    }

    fun getMaps() = viewModelScope.launch {
        getMapsUseCase().onSuccess {
            updateUiState { copy(maps = it) }
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