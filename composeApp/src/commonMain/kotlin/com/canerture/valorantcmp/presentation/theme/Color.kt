package com.canerture.valorantcmp.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF161C23)
val LightBlue = Color(0xFF212830)
val Red = Color(0xFFFF4654)
val White = Color(0xFFFFFDF1)

@Immutable
data class ValorantColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val defaultWhite: Color,
    val defaultBlue: Color,
    val defaultLightBlue: Color,
    val defaultRed: Color,
    val navColors: NavColors,
    val cardBackground: Color,
    val cardBackgroundSecondary: Color,
)

@Immutable
data class NavColors(
    val containerColor: Color,
    val selectedIconColor: Color,
    val selectedTextColor: Color,
    val selectedIndicatorColor: Color,
    val unselectedIconColor: Color,
    val unselectedTextColor: Color,
    val disabledIconColor: Color,
    val disabledTextColor: Color
)
