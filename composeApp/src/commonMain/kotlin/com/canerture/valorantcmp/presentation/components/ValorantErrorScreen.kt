package com.canerture.valorantcmp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.desc_warning_image
import valorantcmp.composeapp.generated.resources.something_went_wrong

@Composable
fun ValorantErrorScreen(
    errorText: String?,
    onTryAgainClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = ValorantTheme.colors.defaultWhite,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Image(
            imageVector = Icons.Rounded.Warning,
            contentDescription = stringResource(Res.string.desc_warning_image),
            modifier = Modifier.aspectRatio(3 / 2f).padding(horizontal = 24.dp),
            colorFilter = ColorFilter.tint(ValorantTheme.colors.primary)
        )

        ValorantText(
            text = errorText ?: stringResource(Res.string.something_went_wrong),
            style = ValorantTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = ValorantTheme.colors.defaultRed),
            onClick = onTryAgainClick
        ) {
            ValorantText(
                text = "Try Again",
                style = ValorantTheme.typography.bodySmall
            )
        }
    }
}
