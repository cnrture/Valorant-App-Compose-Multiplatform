package com.canerture.valorantcmp.presentation.weapons

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponsUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeaponsScreenModel(
    private val getWeaponsUseCase: GetWeaponsUseCase
) : StateScreenModel<WeaponsState>(WeaponsState.Loading) {

    fun getWeapons() = screenModelScope.launch {
        getWeaponsUseCase().onSuccess {
            if (it.isNotEmpty()) {
                setState { WeaponsState.Content(it) }
            } else {
                setState { WeaponsState.Empty }
            }
        }.onFailure {
            setState { WeaponsState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: WeaponsState.() -> WeaponsState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface WeaponsState {
    data object Loading : WeaponsState
    data object Empty : WeaponsState
    data class Content(val weapons: List<WeaponUI>) : WeaponsState
    data class ShowError(val message: String?) : WeaponsState
}
