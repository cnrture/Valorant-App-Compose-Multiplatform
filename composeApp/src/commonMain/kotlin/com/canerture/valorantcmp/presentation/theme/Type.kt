package com.canerture.valorantcmp.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class ValorantTypography {

    val titleLarge: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = ValorantTheme.colors.primary,
            textAlign = TextAlign.Center
        )

    val titleMedium: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = ValorantTheme.colors.primary,
            textAlign = TextAlign.Center
        )

    val titleNormal: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = ValorantTheme.colors.primary,
            textAlign = TextAlign.Center
        )

    val titleSmall: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = ValorantTheme.colors.primary,
            textAlign = TextAlign.Center
        )

    val bodySmall: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = ValorantTheme.colors.primary,
            textAlign = TextAlign.Center
        )
}
