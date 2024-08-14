package com.canerture.valorantcmp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.canerture.valorantcmp.common.NoRippleInteractionSource
import com.canerture.valorantcmp.common.Routes
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.painterResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.ic_agents
import valorantcmp.composeapp.generated.resources.ic_maps
import valorantcmp.composeapp.generated.resources.ic_tiers
import valorantcmp.composeapp.generated.resources.ic_weapons

@Composable
fun ValorantNavigationRail(
    navController: NavController,
) {
    val tabList = listOf(
        NavItem(Routes.AGENTS, "Agents", Res.drawable.ic_agents),
        NavItem(Routes.MAPS, "Maps", Res.drawable.ic_maps),
        NavItem(Routes.WEAPONS, "Weapons", Res.drawable.ic_weapons),
        NavItem(Routes.TIERS, "Tiers", Res.drawable.ic_tiers),
    )

    NavigationRail(
        containerColor = ValorantTheme.colors.navColors.containerColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        tabList.forEach { screen ->
            NavigationRailItem(
                modifier = Modifier.padding(vertical = 16.dp),
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                interactionSource = NoRippleInteractionSource(),
                icon = { Icon(painter = painterResource(screen.icon), contentDescription = null) },
                label = { Text(screen.title) },
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
