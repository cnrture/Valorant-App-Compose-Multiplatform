package com.canerture.valorantcmp.presentation.weapondetail

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.domain.usecase.weapons.GetWeaponDetailUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeaponDetailScreenModel(
    private val getWeaponDetailUseCase: GetWeaponDetailUseCase,
) : StateScreenModel<WeaponDetailState>(WeaponDetailState.Loading) {

    fun getWeaponDetail(id: String) = screenModelScope.launch {
        getWeaponDetailUseCase(id).onSuccess {
            setState { WeaponDetailState.Content(it) }
        }.onFailure {
            setState { WeaponDetailState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: WeaponDetailState.() -> WeaponDetailState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface WeaponDetailState {
    data object Loading : WeaponDetailState
    data class Content(val weapon: WeaponUI) : WeaponDetailState
    data class ShowError(val message: String?) : WeaponDetailState
}
