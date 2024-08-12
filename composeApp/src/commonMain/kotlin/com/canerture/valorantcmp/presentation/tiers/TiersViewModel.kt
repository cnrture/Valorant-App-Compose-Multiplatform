package com.canerture.valorantcmp.presentation.tiers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.domain.usecase.tiers.GetTiersUseCase
import com.canerture.valorantcmp.presentation.tiers.TiersContract.UiEffect
import com.canerture.valorantcmp.presentation.tiers.TiersContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TiersViewModel(
    private val getTiersUseCase: GetTiersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun getTiers() = viewModelScope.launch {
        getTiersUseCase().onSuccess { list ->
            updateUiState { copy(tiers = list) }
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
