package com.canerture.valorantcmp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.canerture.valorantcmp.navigation.ValorantNavigation
import com.canerture.valorantcmp.navigation.ValorantNavigationBar
import com.canerture.valorantcmp.navigation.ValorantNavigationRail
import com.canerture.valorantcmp.presentation.theme.LocalWindowType
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import com.canerture.valorantcmp.presentation.theme.WindowType
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ValorantTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val windowType = LocalWindowType.current
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (windowType == WindowType.Small) {
                        ValorantNavigationBar(navController)
                    }
                },
            ) { innerPadding ->
                if (windowType != WindowType.Small) {
                    Row(
                        Modifier.padding(innerPadding)
                    ) {
                        ValorantNavigationRail(navController)
                        Box(
                            Modifier.weight(1f)
                        ) {
                            ValorantNavigation(navController)
                        }
                    }
                } else {
                    Box(
                        Modifier.padding(innerPadding)
                    ) {
                        ValorantNavigation(navController)
                    }
                }
            }
        }
    }
}


