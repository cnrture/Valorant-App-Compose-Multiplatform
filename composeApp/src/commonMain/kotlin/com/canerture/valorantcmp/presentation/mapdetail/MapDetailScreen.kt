package com.canerture.valorantcmp.presentation.mapdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.collectWithLifecycle
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.presentation.components.ValorantBackIcon
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun MapDetailScreen(
    uiState: MapDetailContract.UiState,
    uiEffect: Flow<MapDetailContract.UiEffect>,
    onAction: (MapDetailContract.UiAction) -> Unit,
    onBackClick: () -> Unit,
) {

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is MapDetailContract.UiEffect.ShowError -> {

            }

            MapDetailContract.UiEffect.GoToBack -> onBackClick()
        }
    }

    uiState.map?.let {
        MapDetailContent(
            map = it,
            onBackClick = {
                onAction(MapDetailContract.UiAction.OnBackClick)
            }
        )
    }

    if (uiState.isLoading) ValorantProgressBar()
}

@Composable
fun MapDetailContent(
    map: MapUI,
    onBackClick: () -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ValorantImage(
                modifier = Modifier
                    .sizeIn(maxWidth = 500.dp, maxHeight = 800.dp)
                    .aspectRatio(1f)
                    .padding(32.dp),
                imageUrl = map.displayIcon,
                contentDescription = map.displayName,
            )

            Spacer(modifier = Modifier.size(24.dp))

            ValorantText(
                text = map.displayName,
                style = ValorantTheme.typography.titleMedium,
            )

            Spacer(modifier = Modifier.size(8.dp))

            ValorantText(
                text = map.coordinates,
                style = ValorantTheme.typography.bodySmall,
            )

            Spacer(modifier = Modifier.size(24.dp))

            ValorantText(
                text = map.narrativeDescription,
                style = ValorantTheme.typography.bodySmall,
            )
        }

        ValorantBackIcon(
            modifier = Modifier.align(Alignment.TopStart),
            padding = 24.dp,
            onBackClick = onBackClick
        )
    }
}
