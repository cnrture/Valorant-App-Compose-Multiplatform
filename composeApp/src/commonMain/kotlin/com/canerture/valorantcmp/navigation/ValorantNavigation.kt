package com.canerture.valorantcmp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailScreen
import com.canerture.valorantcmp.presentation.agentdetail.AgentDetailViewModel
import com.canerture.valorantcmp.presentation.agents.AgentsScreen
import com.canerture.valorantcmp.presentation.agents.AgentsViewModel
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailScreen
import com.canerture.valorantcmp.presentation.mapdetail.MapDetailViewModel
import com.canerture.valorantcmp.presentation.maps.MapsScreen
import com.canerture.valorantcmp.presentation.maps.MapsViewModel
import com.canerture.valorantcmp.presentation.splash.SplashScreen
import com.canerture.valorantcmp.presentation.tiers.TiersScreen
import com.canerture.valorantcmp.presentation.tiers.TiersViewModel
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailScreen
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailViewModel
import com.canerture.valorantcmp.presentation.weapons.WeaponsScreen
import com.canerture.valorantcmp.presentation.weapons.WeaponsViewModel
import org.koin.compose.koinInject

@Composable
fun ValorantNavigation(
    navHostController: NavHostController = rememberNavController(),
) {
    val enterAnim = fadeIn(tween(1000))
    val exitAnim = fadeOut(tween(1000))
    NavHost(
        navController = navHostController,
        startDestination = "splash",
        enterTransition = { enterAnim },
        exitTransition = { exitAnim },
        popEnterTransition = { enterAnim },
        popExitTransition = { exitAnim }
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateAgentsScreen = { navHostController.navigate("agents") }
            )
        }
        composable("agents") {
            val viewModel: AgentsViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getAgents()
            AgentsScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateAgentDetail = { agentId ->
                    navHostController.navigate("agentDetail/$agentId")
                }
            )
        }
        composable("agentDetail/{agentId}") { backStackEntry ->
            val agentId = backStackEntry.arguments?.getString("agentId")
            val viewModel: AgentDetailViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getAgentDetail(agentId.orEmpty())
            AgentDetailScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onBackClick = { navHostController.popBackStack() },
            )
        }
        composable("maps") {
            val viewModel: MapsViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getMaps()
            MapsScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateMapDetail = { mapId ->
                    navHostController.navigate("mapDetail/$mapId")
                }
            )
        }
        composable("mapDetail/{mapId}") { backStackEntry ->
            val mapId = backStackEntry.arguments?.getString("mapId")
            val viewModel: MapDetailViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getMapDetail(mapId.orEmpty())
            MapDetailScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onBackClick = { navHostController.popBackStack() },
            )
        }
        composable("weapons") {
            val viewModel: WeaponsViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getWeapons()
            WeaponsScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onNavigateWeaponDetail = { weaponId ->
                    navHostController.navigate("weaponDetail/$weaponId")
                }
            )
        }
        composable("weaponDetail/{weaponId}") { backStackEntry ->
            val weaponId = backStackEntry.arguments?.getString("weaponId")
            val viewModel: WeaponDetailViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getWeaponDetail(weaponId.orEmpty())
            WeaponDetailScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                onBackClick = { navHostController.popBackStack() },
            )
        }
        composable("tiers") {
            val viewModel: TiersViewModel = koinInject()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            viewModel.getTiers()
            TiersScreen(
                uiState = uiState,
                uiEffect = uiEffect,
            )
        }
    }
}
