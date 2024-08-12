package com.canerture.valorantcmp.presentation.agents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.domain.model.AgentUI
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

private const val ASPECT_RATIO = 3 / 5f
private const val ALPHA = 0.2f

@Composable
fun AgentItem(
    modifier: Modifier = Modifier,
    agent: AgentUI,
    onAgentClick: (String) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .heightIn(max = 400.dp)
                .align(Alignment.Center)
                .padding(horizontal = 12.dp)
                .clickable { onAgentClick(agent.id) }
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(top = 80.dp)
                    .background(
                        brush = Brush.verticalGradient(agent.backgroundGradientColors),
                        shape = RoundedCornerShape(
                            topStart = 120.dp,
                            topEnd = 12.dp,
                            bottomEnd = 12.dp,
                            bottomStart = 12.dp
                        )
                    )
            ) {
                ValorantImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(ASPECT_RATIO)
                        .alpha(ALPHA),
                    imageUrl = agent.background,
                    contentDescription = agent.displayName,
                )
            }

            ValorantImage(
                modifier = Modifier
                    .aspectRatio(ASPECT_RATIO)
                    .align(Alignment.CenterEnd),
                imageUrl = agent.fullPortrait,
                contentDescription = agent.displayName,
            )

            ValorantText(
                text = agent.displayName,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp),
                style = ValorantTheme.typography.titleLarge.copy(color = ValorantTheme.colors.defaultWhite)
            )
        }
    }
}
