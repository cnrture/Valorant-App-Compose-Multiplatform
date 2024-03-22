package com.canerture.valorantcmp.presentation.weapons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.presentation.components.ValorantEmptyScreen
import com.canerture.valorantcmp.presentation.components.ValorantErrorScreen
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import com.canerture.valorantcmp.presentation.weapondetail.WeaponDetailScreen

class WeaponsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: WeaponsScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getWeapons()

        when (state) {
            WeaponsState.Loading -> ValorantProgressBar()

            WeaponsState.Empty -> ValorantEmptyScreen()

            is WeaponsState.Content -> {
                WeaponListContent(
                    weapons = (state as WeaponsState.Content).weapons,
                    onWeaponClick = { id ->
                        navigator.push(WeaponDetailScreen(id))
                    }
                )
            }

            is WeaponsState.ShowError -> {
                ValorantErrorScreen(
                    errorText = (state as WeaponsState.ShowError).message,
                    onTryAgainClick = {
                        screenModel.getWeapons()
                    }
                )
            }
        }
    }

    @Composable
    fun WeaponListContent(
        weapons: List<WeaponUI>,
        onWeaponClick: (String) -> Unit
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(180.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(weapons) { weaponItem ->
                WeaponItem(
                    weapon = weaponItem,
                    onWeaponClick = onWeaponClick
                )
            }
        }
    }
}
