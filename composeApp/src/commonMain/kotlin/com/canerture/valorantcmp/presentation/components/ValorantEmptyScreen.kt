package com.canerture.valorantcmp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.desc_warning_image

private const val ASPECT_RATIO = 3 / 2f

@Composable
fun ValorantEmptyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ValorantTheme.colors.secondary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = Icons.Rounded.WarningAmber,
            contentDescription = stringResource(Res.string.desc_warning_image),
            modifier = Modifier
                .aspectRatio(ASPECT_RATIO)
                .padding(horizontal = 96.dp),
            colorFilter = ColorFilter.tint(ValorantTheme.colors.defaultRed)
        )

        ValorantText(
            text = "No data found!",
            style = ValorantTheme.typography.titleMedium
        )
    }
}
