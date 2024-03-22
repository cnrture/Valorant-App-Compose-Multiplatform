package com.canerture.valorantcmp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.desc_back_icon

@Composable
fun ValorantBackIcon(
    modifier: Modifier = Modifier,
    padding: Dp = 0.dp,
    onBackClick: () -> Unit
) {
    IconButton(
        onClick = onBackClick,
        modifier = modifier
            .padding(padding)
            .border(2.dp, ValorantTheme.colors.primary, RoundedCornerShape(6.dp))
            .size(36.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ChevronLeft,
            tint = ValorantTheme.colors.primary,
            contentDescription = stringResource(Res.string.desc_back_icon)
        )
    }
}
