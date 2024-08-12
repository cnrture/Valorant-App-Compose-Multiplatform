package com.canerture.valorantcmp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.ic_agents
import valorantcmp.composeapp.generated.resources.ic_maps
import valorantcmp.composeapp.generated.resources.ic_tiers
import valorantcmp.composeapp.generated.resources.ic_weapons

data class NavItem(
    val route: String,
    val title: String,
    val icon: Painter
)

@Composable
fun TabList() = listOf(
    NavItem("agents", "Agents", painterResource(Res.drawable.ic_agents)),
    NavItem("maps", "Maps", painterResource(Res.drawable.ic_maps)),
    NavItem("weapons", "Weapons", painterResource(Res.drawable.ic_weapons)),
    NavItem("tiers", "Tiers", painterResource(Res.drawable.ic_tiers)),
)