package com.canerture.valorantcmp.presentation.weapons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponsUseCase
import com.canerture.valorantcmp.presentation.weapons.WeaponsContract.UiAction
import com.canerture.valorantcmp.presentation.weapons.WeaponsContract.UiEffect
import com.canerture.valorantcmp.presentation.weapons.WeaponsContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeaponsViewModel(
    private val getWeaponsUseCase: GetWeaponsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(uiAction: UiAction) = viewModelScope.launch {
        when (uiAction) {
            is UiAction.OnWeaponClick -> emitUiEffect(UiEffect.GoToWeaponDetail(uiAction.id))
        }
    }

    fun getWeapons() = viewModelScope.launch {
        getWeaponsUseCase().onSuccess {
            updateUiState { copy(weapons = it) }
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