package com.canerture.valorantcmp.presentation.weapons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponsUseCase
import com.canerture.valorantcmp.presentation.weapons.WeaponsContract.UiAction
import com.canerture.valorantcmp.presentation.weapons.WeaponsContract.UiEffect
import com.canerture.valorantcmp.presentation.weapons.WeaponsContract.UiState
import kotlinx.coroutines.launch

class WeaponsViewModel(
    private val getWeaponsUseCase: GetWeaponsUseCase
) : ViewModel(), MVI<UiState, UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnWeaponClick -> emitUiEffect(UiEffect.GoToWeaponDetail(uiAction.id))
            }
        }
    }

    fun getWeapons() = viewModelScope.launch {
        updateUiState { copy(isLoading = true) }
        getWeaponsUseCase().onSuccess {
            updateUiState { copy(isLoading = false, weapons = it) }
        }.onFailure {
            updateUiState { copy(isLoading = false) }
            emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
        }
    }
}