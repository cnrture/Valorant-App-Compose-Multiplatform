package com.canerture.valorantcmp.presentation.theme

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@Composable
fun rememberWindowType(): WindowType {
    val type = calculateWindowSizeClass().widthSizeClass
    return if (type <= WindowWidthSizeClass.Compact) {
        WindowType.Small
    } else if (type <= WindowWidthSizeClass.Medium) {
        WindowType.Medium
    } else {
        WindowType.Large
    }
}

enum class WindowType {
    Small, Medium, Large
}
