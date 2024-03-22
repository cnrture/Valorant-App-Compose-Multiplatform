package com.canerture.valorantcmp.presentation.competitivetiers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.canerture.valorantcmp.domain.model.TierUI
import com.canerture.valorantcmp.presentation.components.ValorantEmptyScreen
import com.canerture.valorantcmp.presentation.components.ValorantErrorScreen
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar

class CompetitiveTiersScreen : Screen {
    @Composable
    override fun Content() {

        val screenModel: CompetitiveTiersScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getCompetitiveTiers()

        when (state) {
            CompetitiveTiersState.Loading -> ValorantProgressBar()

            CompetitiveTiersState.Empty -> ValorantEmptyScreen()

            is CompetitiveTiersState.Content -> {
                TierListContent(
                    tiers = (state as CompetitiveTiersState.Content).tier
                )
            }


            is CompetitiveTiersState.ShowError -> {
                ValorantErrorScreen(
                    errorText = (state as CompetitiveTiersState.ShowError).message,
                    onTryAgainClick = {
                        screenModel.getCompetitiveTiers()
                    }
                )
            }
        }
    }

    @Composable
    fun TierListContent(
        tiers: List<TierUI>
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(tiers) { tierItem ->
                CompetitiveTierItem(
                    tier = tierItem
                )
            }
        }
    }
}
