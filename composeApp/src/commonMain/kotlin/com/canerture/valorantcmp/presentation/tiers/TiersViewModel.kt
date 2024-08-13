package com.canerture.valorantcmp.presentation.tiers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canerture.valorantcmp.delegation.MVI
import com.canerture.valorantcmp.delegation.mvi
import com.canerture.valorantcmp.domain.usecase.tiers.GetTiersUseCase
import com.canerture.valorantcmp.presentation.tiers.TiersContract.UiEffect
import com.canerture.valorantcmp.presentation.tiers.TiersContract.UiState
import kotlinx.coroutines.launch

class TiersViewModel(
    private val getTiersUseCase: GetTiersUseCase
) : ViewModel(), MVI<UiState, Unit, UiEffect> by mvi(UiState()) {

    fun getTiers() {
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            getTiersUseCase().onSuccess { list ->
                updateUiState { copy(isLoading = false, tiers = list) }
            }.onFailure {
                updateUiState { copy(isLoading = false) }
                emitUiEffect(UiEffect.ShowError(it.message.orEmpty()))
            }
        }
    }
}
