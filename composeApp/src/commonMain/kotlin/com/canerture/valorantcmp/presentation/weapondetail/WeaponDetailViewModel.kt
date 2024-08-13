package com.canerture.valorantcmp.presentation.weapondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponDetailUseCase
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailContract.UiAction
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailContract.UiEffect
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailContract.UiState
import kotlinx.coroutines.launch

class WeaponDetailViewModel(
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase,
) : ViewModel(), MVI<UiState, UiAction, UiEffect> by mvi(UiState()) {

    override fun onAction(uiAction: UiAction) {
        viewModelScope.launch {
            when (uiAction) {
                is UiAction.OnBackClick -> emitUiEffect(UiEffect.GoToBack)
            }
        }
    }

    fun getWeaponDetail(id: String) {
        viewModelScope.launch {
            updateUiState { copy(weapon = null) }
            getWeaponDetailUseCase(id).onSuccess {
                updateUiState { copy(isLoading = false, weapon = it) }
            }.onFailure {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }
}