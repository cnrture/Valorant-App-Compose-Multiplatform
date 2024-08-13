package com.canerture.valorantcmp.delegation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

context(ViewModel)
fun <UiState, UiAction, UiEffect> MVI<UiState, UiAction, UiEffect>.emitUiEffectUsingVMScope(
    uiEffect: UiEffect,
) {
    viewModelScope.launch {
        emitUiEffect(uiEffect)
    }
}