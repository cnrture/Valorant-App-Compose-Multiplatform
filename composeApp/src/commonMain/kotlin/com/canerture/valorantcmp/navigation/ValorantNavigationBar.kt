package com.canerture.valorantcmp.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.canerture.valorantcmp.common.Routes
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.ic_agents
import valorantcmp.composeapp.generated.resources.ic_maps
import valorantcmp.composeapp.generated.resources.ic_tiers
import valorantcmp.composeapp.generated.resources.ic_weapons

data class NavItem(
    val route: String,
    val title: String,
    val icon: DrawableResource
)

@Composable
fun ValorantNavigationBar(
    navController: NavController,
) {
    val tabList = listOf(
        NavItem(Routes.AGENTS, "Agents", Res.drawable.ic_agents),
        NavItem(Routes.MAPS, "Maps", Res.drawable.ic_maps),
        NavItem(Routes.WEAPONS, "Weapons", Res.drawable.ic_weapons),
        NavItem(Routes.TIERS, "Tiers", Res.drawable.ic_tiers),
    )

    NavigationBar(
        containerColor = ValorantTheme.colors.navColors.containerColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        tabList.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                icon = { Icon(painter = painterResource(screen.icon), contentDescription = null) },
                label = { Text(screen.title) },
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = ValorantTheme.colors.navColors.selectedIconColor,
                    selectedTextColor = ValorantTheme.colors.navColors.selectedTextColor,
                    selectedIndicatorColor = ValorantTheme.colors.navColors.selectedIndicatorColor,
                    unselectedIconColor = ValorantTheme.colors.navColors.unselectedIconColor,
                    unselectedTextColor = ValorantTheme.colors.navColors.unselectedTextColor,
                    disabledIconColor = ValorantTheme.colors.navColors.disabledIconColor,
                    disabledTextColor = ValorantTheme.colors.navColors.disabledTextColor
                )
            )
        }
    }
}
