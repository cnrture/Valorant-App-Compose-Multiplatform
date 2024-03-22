package com.canerture.valorantcmp.presentation.maps

import androidx.compose.foundation.layout.Arrangement
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.presentation.components.ValorantEmptyScreen
import com.canerture.valorantcmp.presentation.components.ValorantErrorScreen
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailScreen

class MapsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: MapsScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getMaps()

        when (state) {
            MapsState.Loading -> ValorantProgressBar()

            MapsState.Empty -> ValorantEmptyScreen()

            is MapsState.Content -> {
                MapListContent(
                    maps = (state as MapsState.Content).maps,
                    onMapClick = { id ->
                        navigator.push(MapDetailScreen(id))
                    }
                )
            }

            is MapsState.ShowError -> {
                ValorantErrorScreen(
                    errorText = (state as MapsState.ShowError).message,
                    onTryAgainClick = {
                        screenModel.getMaps()
                    }
                )
            }
        }
    }

    @Composable
    fun MapListContent(
        maps: List<MapUI>,
        onMapClick: (String) -> Unit
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            items(maps) { mapItem ->
                MapItem(
                    map = mapItem,
                    onMapClick = onMapClick
                )
            }
        }
    }
}
