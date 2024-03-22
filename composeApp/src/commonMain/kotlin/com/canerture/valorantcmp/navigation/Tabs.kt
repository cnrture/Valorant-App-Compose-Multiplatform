package com.canerture.valorantcmp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.canerture.valorantcmp.presentation.agents.AgentsScreen
import com.canerture.valorantcmp.presentation.competitivetiers.CompetitiveTiersScreen
import com.canerture.valorantcmp.presentation.maps.MapsScreen
import com.canerture.valorantcmp.presentation.weapons.WeaponsScreen
import org.jetbrains.compose.resources.painterResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.ic_agents
import valorantcmp.composeapp.generated.resources.ic_maps
import valorantcmp.composeapp.generated.resources.ic_tiers
import valorantcmp.composeapp.generated.resources.ic_weapons

data class NavItem(
    val screen: Tab,
    val title: String,
    val icon: Painter
)

@Composable
fun TabList() = listOf(
    NavItem(Tabs.AgentsTab, "Agents", painterResource(Res.drawable.ic_agents)),
    NavItem(Tabs.MapsTab, "Maps", painterResource(Res.drawable.ic_maps)),
    NavItem(Tabs.WeaponsTab, "Weapons", painterResource(Res.drawable.ic_weapons)),
    NavItem(Tabs.CompetitiveTiersTab, "Tiers", painterResource(Res.drawable.ic_tiers)),
)

object Tabs {
    object AgentsTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val tab = TabList()[TAB_ONE]
                return remember { TabOptions(index = 0u, title = tab.title, icon = tab.icon) }
            }

        @Composable
        override fun Content() {
            Navigator(AgentsScreen())
        }
    }

    object MapsTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val tab = TabList()[TAB_TWO]
                return remember { TabOptions(index = 1u, title = tab.title, icon = tab.icon) }
            }

        @Composable
        override fun Content() {
            Navigator(MapsScreen())
        }
    }

    object WeaponsTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val tab = TabList()[TAB_THREE]
                return remember { TabOptions(index = 2u, title = tab.title, icon = tab.icon) }
            }

        @Composable
        override fun Content() {
            Navigator(WeaponsScreen())
        }
    }

    object CompetitiveTiersTab : Tab {
        override val options: TabOptions
            @Composable
            get() {
                val tab = TabList()[TAB_FOUR]
                return remember { TabOptions(index = 3u, title = tab.title, icon = tab.icon) }
            }

        @Composable
        override fun Content() {
            Navigator(CompetitiveTiersScreen())
        }
    }

    private const val TAB_ONE = 0
    private const val TAB_TWO = 1
    private const val TAB_THREE = 2
    private const val TAB_FOUR = 3
}
