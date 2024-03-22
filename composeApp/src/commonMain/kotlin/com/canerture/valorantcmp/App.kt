package com.canerture.valorantcmp

import androidx.compose.runtime.Composable
import com.canerture.valorantcmp.navigation.ValorantNavigator
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ValorantTheme {
        ValorantNavigator()
    }
}


