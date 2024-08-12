package com.canerture.valorantcmp.presentation.agentdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.collectWithLifecycle
import com.canerture.valorantcmp.domain.model.AbilityUI
import com.canerture.valorantcmp.domain.model.AgentUI
import com.canerture.valorantcmp.presentation.components.ValorantBackIcon
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantTabRow
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.LocalWindowType
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import com.canerture.valorantcmp.presentation.theme.WindowType
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.title_abilities
import valorantcmp.composeapp.generated.resources.title_description

private const val ASPECT_RATIO = 16 / 9f
private const val ASPECT_RATIO_MOBILE = 3 / 2f
private const val ASPECT_RATIO_MOBILE_FULL = 1f

@Composable
fun AgentDetailScreen(
    uiState: AgentDetailContract.UiState,
    uiEffect: Flow<AgentDetailContract.UiEffect>,
    onAction: (AgentDetailContract.UiAction) -> Unit,
    onBackClick: () -> Unit,
) {
    val windowType = LocalWindowType.current

    uiEffect.collectWithLifecycle {
        when (it) {
            AgentDetailContract.UiEffect.GoToBack -> onBackClick()
            is AgentDetailContract.UiEffect.ShowError -> {
                // Show toast
            }
        }
    }

    uiState.agent?.let { agent ->
        if (windowType == WindowType.Large) {
            DetailDesktopContent(
                agent = agent,
                onBackClick = {
                    onAction(AgentDetailContract.UiAction.OnBackClick)
                }
            )
        } else {
            DetailMobileContent(
                agent = agent,
                onBackClick = {
                    onAction(AgentDetailContract.UiAction.OnBackClick)
                }
            )
        }
    }
}

@Composable
fun DetailDesktopContent(
    agent: AgentUI,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val agentColor = agent.backgroundGradientColors[1]

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                ValorantBackIcon(
                    padding = 12.dp,
                    onBackClick = onBackClick
                )

                Spacer(modifier = Modifier.height(24.dp))

                ValorantText(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = agent.displayName,
                    style = ValorantTheme.typography.titleLarge.copy(color = agentColor)
                )

                Spacer(modifier = Modifier.height(16.dp))

                ValorantText(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = agent.description,
                    style = ValorantTheme.typography.bodySmall.copy(textAlign = TextAlign.Start),
                )

                Spacer(modifier = Modifier.height(16.dp))

                AbilitiesTabLayout(
                    abilities = agent.abilities,
                    agentColor = agentColor
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(agent.backgroundGradientColors),
                        shape = RoundedCornerShape(
                            topStart = 120.dp
                        )
                    )
                    .padding(16.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                ValorantImage(
                    modifier = Modifier.widthIn(max = 400.dp).aspectRatio(ASPECT_RATIO),
                    imageUrl = agent.background,
                    contentDescription = agent.displayName,
                )

                ValorantImage(
                    modifier = Modifier.widthIn(max = 400.dp).aspectRatio(ASPECT_RATIO),
                    imageUrl = agent.fullPortrait,
                    contentDescription = agent.displayName,
                )
            }
        }
    }
}

@Composable
fun DetailMobileContent(
    agent: AgentUI,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val agentColor = agent.backgroundGradientColors[1]
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(agent.backgroundGradientColors),
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ValorantImage(
                modifier = Modifier.widthIn(max = 400.dp).aspectRatio(ASPECT_RATIO_MOBILE),
                imageUrl = agent.background,
                contentDescription = agent.displayName,
            )

            ValorantImage(
                modifier = Modifier.widthIn(max = 400.dp).aspectRatio(ASPECT_RATIO_MOBILE_FULL),
                imageUrl = agent.fullPortrait,
                contentDescription = agent.displayName,
            )

            ValorantBackIcon(
                modifier = Modifier.align(Alignment.TopStart),
                padding = 12.dp,
                onBackClick = onBackClick
            )
        }

        Spacer(modifier = Modifier.size(24.dp))

        ValorantText(
            text = stringResource(Res.string.title_description),
            style = ValorantTheme.typography.titleMedium.copy(color = agentColor),
        )

        Spacer(modifier = Modifier.size(8.dp))

        ValorantText(
            modifier = Modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
            text = agent.description,
            style = ValorantTheme.typography.bodySmall,
        )

        Spacer(modifier = Modifier.size(24.dp))

        ValorantText(
            text = stringResource(Res.string.title_abilities),
            style = ValorantTheme.typography.titleMedium.copy(color = agentColor),
        )

        Spacer(modifier = Modifier.size(8.dp))

        AbilitiesTabLayout(
            abilities = agent.abilities,
            agentColor = agentColor
        )
    }
}

@Composable
fun AbilitiesTabLayout(
    abilities: List<AbilityUI>,
    agentColor: Color
) {
    val pagerState = rememberPagerState(pageCount = { abilities.size })

    ValorantTabRow.Icon(
        items = abilities,
        pagerState = pagerState,
        image = { ability -> ability.displayIcon },
        contentDescription = { ability -> ability.displayName },
        iconColors = Pair(ValorantTheme.colors.defaultWhite, ValorantTheme.colors.navColors.unselectedIconColor),
        bgColors = Pair(agentColor, ValorantTheme.colors.secondary),
        pageContent = { ability ->
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
                ValorantText(
                    text = ability.displayName,
                    style = ValorantTheme.typography.titleSmall.copy(color = agentColor)
                )

                ValorantText(
                    modifier = Modifier.padding(16.dp),
                    text = ability.description,
                    style = ValorantTheme.typography.bodySmall,
                )
            }
        }
    )
}