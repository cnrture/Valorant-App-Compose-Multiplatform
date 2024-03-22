package com.canerture.valorantcmp.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.canerture.valorantcmp.common.NoRippleInteractionSource
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

@Composable
fun ValorantNavigationBar() {
    val tabNavigator = LocalTabNavigator.current

    NavigationBar(
        containerColor = ValorantTheme.colors.navColors.containerColor,
    ) {
        TabList().forEach { item ->
            NavigationBarItem(
                selected = tabNavigator.current == item.screen,
                onClick = { tabNavigator.current = item.screen },
                interactionSource = NoRippleInteractionSource(),
                label = { Text(text = item.title) },
                icon = { Icon(item.icon, item.title) },
                colors = NavigationBarItemColors(
                    selectedIconColor = ValorantTheme.colors.navColors.selectedIconColor,
                    selectedTextColor = ValorantTheme.colors.navColors.selectedTextColor,
                    selectedIndicatorColor = ValorantTheme.colors.navColors.selectedIndicatorColor,
                    unselectedIconColor = ValorantTheme.colors.navColors.unselectedIconColor,
                    unselectedTextColor = ValorantTheme.colors.navColors.unselectedTextColor,
                    disabledIconColor = ValorantTheme.colors.navColors.disabledIconColor,
                    disabledTextColor = ValorantTheme.colors.navColors.disabledTextColor
                ),
                alwaysShowLabel = false
            )
        }
    }
}
