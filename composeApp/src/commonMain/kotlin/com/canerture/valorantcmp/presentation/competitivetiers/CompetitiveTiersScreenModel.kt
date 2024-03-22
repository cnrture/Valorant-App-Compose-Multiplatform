package com.canerture.valorantcmp.presentation.competitivetiers

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.canerture.valorantcmp.domain.model.TierUI
import com.canerture.valorantcmp.domain.usecase.competitivetiers.GetCompetitiveTiersUseCase
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CompetitiveTiersScreenModel(
    private val getCompetitiveTiersUseCase: GetCompetitiveTiersUseCase
) : StateScreenModel<CompetitiveTiersState>(CompetitiveTiersState.Loading) {

    fun getCompetitiveTiers() = screenModelScope.launch {
        getCompetitiveTiersUseCase().onSuccess { list ->
            if (list.isNotEmpty()) {
                setState { CompetitiveTiersState.Content(list) }
            } else {
                setState { CompetitiveTiersState.Empty }
            }
        }.onFailure {
            setState { CompetitiveTiersState.ShowError(it.message) }
        }
    }

    private fun setState(reducer: CompetitiveTiersState.() -> CompetitiveTiersState) {
        mutableState.update {
            reducer(it)
        }
    }
}

sealed interface CompetitiveTiersState {
    data object Loading : CompetitiveTiersState
    data object Empty : CompetitiveTiersState
    data class Content(val tier: List<TierUI>) : CompetitiveTiersState
    data class ShowError(val message: String?) : CompetitiveTiersState
}
