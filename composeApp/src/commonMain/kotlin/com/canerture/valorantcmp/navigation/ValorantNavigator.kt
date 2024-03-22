package com.canerture.valorantcmp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.canerture.valorantcmp.presentation.splash.SplashScreen
import com.canerture.valorantcmp.presentation.theme.LocalWindowType
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import com.canerture.valorantcmp.presentation.theme.WindowType

@Composable
fun ValorantNavigator() {
    Navigator(SplashScreen()) {
        FadeTransition(it)
    }
}

class MainNavigator : Screen {
    @Composable
    override fun Content() {
        val windowType = LocalWindowType.current
        TabNavigator(Tabs.AgentsTab) {
            Scaffold(
                containerColor = ValorantTheme.colors.background,
                content = {
                    if (windowType != WindowType.Small) {
                        Row(
                            Modifier.padding(it)
                        ) {
                            ValorantNavigationRail()
                            CurrentTab()
                        }
                    } else {
                        Box(
                            Modifier.padding(it)
                        ) {
                            CurrentTab()
                        }
                    }
                },
                bottomBar = {
                    if (windowType == WindowType.Small) {
                        ValorantNavigationBar()
                    }
                }
            )
        }
    }
}
