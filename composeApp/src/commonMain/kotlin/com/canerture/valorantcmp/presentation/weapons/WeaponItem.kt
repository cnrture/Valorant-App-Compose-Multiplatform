package com.canerture.valorantcmp.presentation.weapons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

private const val ASPECT_RATIO = 16 / 9f

@Composable
fun WeaponItem(
    weapon: WeaponUI,
    onWeaponClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onWeaponClick(weapon.id) }
            .padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = ValorantTheme.colors.cardBackgroundSecondary)
    ) {
        Box(contentAlignment = Alignment.Center) {
            ValorantImage(
                modifier = Modifier.aspectRatio(ASPECT_RATIO).padding(32.dp),
                imageUrl = weapon.displayIcon,
                contentScale = ContentScale.Fit,
                contentDescription = weapon.displayName
            )

            ValorantText(
                text = weapon.displayName,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                style = ValorantTheme.typography.titleSmall
            )
        }
    }
}
