package com.canerture.valorantcmp.presentation.weapons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.collectWithLifecycle
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import kotlinx.coroutines.flow.Flow

@Composable
fun WeaponsScreen(
    uiState: WeaponsContract.UiState,
    uiEffect: Flow<WeaponsContract.UiEffect>,
    onAction: (WeaponsContract.UiAction) -> Unit,
    onNavigateWeaponDetail: (String) -> Unit,
) {
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is WeaponsContract.UiEffect.GoToWeaponDetail -> {
                onNavigateWeaponDetail(effect.id)
            }

            is WeaponsContract.UiEffect.ShowError -> {

            }
        }
    }

    WeaponListContent(
        weapons = uiState.weapons,
        onWeaponClick = { id ->
            onAction(WeaponsContract.UiAction.OnWeaponClick(id))
        }
    )

    if (uiState.isLoading) ValorantProgressBar()
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
