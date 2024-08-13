package com.canerture.valorantcmp.delegation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class MVIDelegate<UiState, UiAction, UiEffect>(initialUiState: UiState) : MVI<UiState, UiAction, UiEffect> {

    override val uiState: StateFlow<UiState>
        field = MutableStateFlow(initialUiState)

    private val _uiEffect by lazy { Channel<UiEffect>() }
    override val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    override fun onAction(uiAction: UiAction) = Unit

    override fun updateUiState(block: UiState.() -> UiState) {
        uiState.update(block)
    }

    override suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}