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

private const val DURATION = 900

@Composable
fun ValorantProgressBar() {
    val progressValue = 1f
    val infiniteTransition = rememberInfiniteTransition(
        label = "stringResource(R.string.progress_bar_transition)"
    )

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue,
        animationSpec = infiniteRepeatable(animation = tween(DURATION)),
        label = "stringResource(R.string.progress_bar_animation)"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progressAnimationValue },
            modifier = Modifier
                .wrapContentSize(),
            color = ValorantTheme.colors.secondary,
        )
    }
}
