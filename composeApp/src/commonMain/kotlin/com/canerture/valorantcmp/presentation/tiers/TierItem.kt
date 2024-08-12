package com.canerture.valorantcmp.presentation.tiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.colorParse
import com.canerture.valorantcmp.domain.model.TierUI
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

@Composable
fun CompetitiveTierItem(
    tier: TierUI
) {
    Card(
        modifier = Modifier.padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = colorParse(tier.backgroundColor))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ValorantImage(
                modifier = Modifier.aspectRatio(1f).padding(24.dp),
                imageUrl = tier.largeIcon,
                contentDescription = tier.tierName
            )
            ValorantText(
                text = tier.tierName,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                ValorantTheme.colors.defaultBlue
                            )
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                style = ValorantTheme.typography.titleSmall.copy(color = ValorantTheme.colors.defaultWhite)
            )
        }
    }
}
