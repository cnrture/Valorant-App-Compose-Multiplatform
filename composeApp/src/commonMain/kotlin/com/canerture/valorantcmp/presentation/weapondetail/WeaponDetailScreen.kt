package com.canerture.valorantcmp.presentation.weapondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.canerture.valorantcmp.domain.model.DamageRangeUI
import com.canerture.valorantcmp.domain.model.SkinUI
import com.canerture.valorantcmp.domain.model.WeaponUI
import com.canerture.valorantcmp.presentation.components.LinearProgress
import com.canerture.valorantcmp.presentation.components.ValorantBackIcon
import com.canerture.valorantcmp.presentation.components.ValorantErrorScreen
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import com.canerture.valorantcmp.presentation.components.ValorantTabRow
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.text_body
import valorantcmp.composeapp.generated.resources.text_head
import valorantcmp.composeapp.generated.resources.text_leg
import valorantcmp.composeapp.generated.resources.title_damage_range
import valorantcmp.composeapp.generated.resources.title_skins

data class WeaponDetailScreen(val id: String) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel: WeaponDetailScreenModel = getScreenModel()

        val state by screenModel.state.collectAsState()

        screenModel.getWeaponDetail(id)

        when (state) {
            WeaponDetailState.Loading -> ValorantProgressBar()

            is WeaponDetailState.Content -> {
                DetailContent(
                    weapon = (state as WeaponDetailState.Content).weapon,
                    onBackClick = {
                        navigator.pop()
                    }
                )
            }

            is WeaponDetailState.ShowError -> {
                ValorantErrorScreen(
                    errorText = (state as WeaponDetailState.ShowError).message,
                    onTryAgainClick = {
                        screenModel.getWeaponDetail(id)
                    }
                )
            }
        }
    }

    @Composable
    fun DetailContent(
        weapon: WeaponUI,
        onBackClick: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = ValorantTheme.colors.defaultRed,
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ValorantImage(
                        modifier = Modifier
                            .widthIn(min = 300.dp, max = 500.dp)
                            .padding(start = 32.dp, end = 32.dp, top = 56.dp),
                        imageUrl = weapon.displayIcon,
                        contentDescription = weapon.displayName,
                    )

                    Spacer(modifier = Modifier.size(24.dp))

                    ValorantText(
                        text = weapon.displayName,
                        style = ValorantTheme.typography.titleMedium.copy(color = ValorantTheme.colors.secondary)
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    ValorantText(
                        text = weapon.category,
                        style = ValorantTheme.typography.titleNormal.copy(color = ValorantTheme.colors.secondary)
                    )
                }

                ValorantBackIcon(
                    modifier = Modifier.align(Alignment.TopStart),
                    onBackClick = onBackClick
                )
            }

            Spacer(modifier = Modifier.size(24.dp))

            ValorantText(
                text = stringResource(Res.string.title_damage_range),
                style = ValorantTheme.typography.titleNormal
            )

            Spacer(modifier = Modifier.size(16.dp))

            DamageRangesTabLayout(weapon.weaponStats.damageRanges)

            Spacer(modifier = Modifier.size(16.dp))

            ValorantText(
                text = stringResource(Res.string.title_skins),
                style = ValorantTheme.typography.titleNormal
            )

            Spacer(modifier = Modifier.size(16.dp))

            SkinsTabLayout(weapon.skins)
        }
    }

    @Composable
    fun DamageRangesTabLayout(
        damageRanges: List<DamageRangeUI>
    ) {
        val pagerState = rememberPagerState(pageCount = { damageRanges.size })

        ValorantTabRow.Text(
            items = damageRanges,
            pagerState = pagerState,
            text = { "${it.rangeStartMeters} - ${it.rangeEndMeters}m" },
            textColors = ValorantTheme.colors.defaultWhite to ValorantTheme.colors.defaultRed,
            bgColors = ValorantTheme.colors.defaultLightBlue to ValorantTheme.colors.defaultBlue,
            pageContent = { damageRange ->
                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp)
                ) {
                    LinearProgress(
                        header = stringResource(Res.string.text_head),
                        progress = damageRange.headDamage,
                        progressString = damageRange.headDamage.toString()
                    )
                    LinearProgress(
                        header = stringResource(Res.string.text_body),
                        progress = damageRange.bodyDamage,
                        progressString = damageRange.bodyDamage.toString()
                    )
                    LinearProgress(
                        header = stringResource(Res.string.text_leg),
                        progress = damageRange.legDamage,
                        progressString = damageRange.legDamage.toString()
                    )
                }
            }
        )
    }

    @Composable
    fun SkinsTabLayout(
        skins: List<SkinUI>
    ) {
        val pagerState = rememberPagerState(pageCount = { skins.size })

        ValorantTabRow.Scrollable(
            items = skins,
            pagerState = pagerState,
            image = { skin -> skin.displayIcon },
            bgColors = ValorantTheme.colors.primary to ValorantTheme.colors.secondary,
            contentDescription = { skin -> skin.displayName },
            pageContent = { skin ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(
                            color = ValorantTheme.colors.cardBackground,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ValorantImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp, vertical = 16.dp),
                        imageUrl = skin.displayIcon,
                        contentScale = ContentScale.Fit,
                        contentDescription = skin.displayName,
                    )

                    ValorantText(
                        text = skin.displayName,
                        modifier = Modifier.padding(16.dp),
                        style = ValorantTheme.typography.titleNormal
                    )
                }
            }
        )
    }
}
