package com.canerture.valorantcmp.presentation.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.progress_bar_animation

private const val DURATION = 1000

@Composable
fun ValorantProgressBar() {
    val infiniteTransition = rememberInfiniteTransition(
        label = stringResource(Res.string.progress_bar_animation)
    )

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(DURATION)),
        label = stringResource(Res.string.progress_bar_animation)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantTheme.colors.secondary),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progressAnimationValue },
            modifier = Modifier
                .wrapContentSize(),
            color = ValorantTheme.colors.primary,
        )
    }
}
