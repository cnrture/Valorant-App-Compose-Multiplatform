package com.canerture.valorantcmp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import com.canerture.valorantcmp.common.NoRippleInteractionSource
import com.canerture.valorantcmp.presentation.theme.ValorantTheme

@Composable
fun ValorantNavigationRail() {
    val tabNavigator = LocalTabNavigator.current

    NavigationRail(
        containerColor = ValorantTheme.colors.navColors.containerColor,
    ) {
        TabList().forEach { item ->
            NavigationRailItem(
                modifier = Modifier.padding(vertical = 16.dp),
                selected = tabNavigator.current == item.screen,
                onClick = { tabNavigator.current = item.screen },
                interactionSource = NoRippleInteractionSource(),
                label = { Text(item.title) },
                icon = { Icon(item.icon, item.title) },
                colors = NavigationRailItemColors(
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
