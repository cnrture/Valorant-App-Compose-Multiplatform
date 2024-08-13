package com.canerture.valorantcmp.presentation.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.collectWithLifecycle
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import kotlinx.coroutines.flow.Flow

@Composable
fun MapsScreen(
    uiState: MapsContract.UiState,
    uiEffect: Flow<MapsContract.UiEffect>,
    onAction: (MapsContract.UiAction) -> Unit,
    onNavigateMapDetail: (String) -> Unit
) {
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is MapsContract.UiEffect.GoToMapDetail -> onNavigateMapDetail(effect.id)

            is MapsContract.UiEffect.ShowError -> {
                // Show error
            }
        }
    }

    MapListContent(
        maps = uiState.maps,
        onMapClick = { id ->
            onAction(MapsContract.UiAction.OnMapClick(id))
        }
    )

    if (uiState.isLoading) ValorantProgressBar()
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

