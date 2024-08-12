package com.canerture.valorantcmp.presentation.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.domain.model.MapUI
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

private const val ASPECT_RATIO = 9 / 16f

@Composable
fun MapItem(
    map: MapUI,
    onMapClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable { onMapClick(map.id) }
    ) {
        Box {
            ValorantImage(
                modifier = Modifier.aspectRatio(ASPECT_RATIO),
                imageUrl = map.splash,
                contentScale = ContentScale.Crop,
                contentDescription = map.displayName
            )

            ValorantText(
                text = map.displayName,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Gray))
                    )
                    .padding(vertical = 12.dp),
                style = ValorantTheme.typography.titleNormal.copy(color = ValorantTheme.colors.defaultWhite)
            )
        }
    }
}
