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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.presentation.components.ValorantBackIcon
import com.canerture.valorantcmp.presentation.components.ValorantErrorScreen
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

data class MapDetailScreen(val id: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: MapDetailScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getMapDetail(id)

        when (state) {
            MapDetailState.Loading -> ValorantProgressBar()

            is MapDetailState.Content -> {
                MapDetailContent(
                    map = (state as MapDetailState.Content).map,
                    onBackClick = {
                        navigator.pop()
                    }
                )
            }

            is MapDetailState.ShowError -> {
                ValorantErrorScreen(
                    errorText = (state as MapDetailState.ShowError).message,
                    onTryAgainClick = {
                        screenModel.getMapDetail(id)
                    }
                )
            }
        }
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
}
