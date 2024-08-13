package com.canerture.valorantcmp.presentation.agents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.carouselTransition
import com.canerture.valorantcmp.common.collectWithLifecycle
import com.canerture.valorantcmp.domain.model.AgentGroupUI
import com.canerture.valorantcmp.presentation.components.ValorantImage
import com.canerture.valorantcmp.presentation.components.ValorantProgressBar
import com.canerture.valorantcmp.presentation.components.ValorantText
import com.canerture.valorantcmp.presentation.theme.LocalWindowType
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import com.canerture.valorantcmp.presentation.theme.WindowType
import kotlinx.coroutines.flow.Flow

@Composable
fun AgentsScreen(
    uiState: AgentsContract.UiState,
    uiEffect: Flow<AgentsContract.UiEffect>,
    onAction: (AgentsContract.UiAction) -> Unit,
    onNavigateAgentDetail: (String) -> Unit,
) {
    val windowType = LocalWindowType.current

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is AgentsContract.UiEffect.ShowError -> {

            }

            is AgentsContract.UiEffect.GoToAgentDetail -> onNavigateAgentDetail(effect.id)
        }
    }

    if (windowType != WindowType.Large) {
        AgentListMobileContent(
            agents = uiState.agents,
            pagerPadding = when (windowType) {
                WindowType.Small -> 64.dp
                WindowType.Medium -> 200.dp
                WindowType.Large -> 240.dp
            },
            onAgentClick = { agentId ->
                onAction(AgentsContract.UiAction.OnAgentClick(agentId))
            }
        )
    } else {
        AgentListDesktopContent(
            agents = uiState.agents,
            onAgentClick = { agentId ->
                onAction(AgentsContract.UiAction.OnAgentClick(agentId))
            }
        )
    }

    if (uiState.isLoading) ValorantProgressBar()
}

@Composable
fun AgentListMobileContent(
    agents: List<AgentGroupUI>,
    pagerPadding: Dp,
    onAgentClick: (String) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
        items(agents) {
            Box(modifier = Modifier.padding(vertical = 32.dp)) {
                val pagerState = rememberPagerState(
                    pageCount = { agents.size },
                    initialPage = 1
                )

                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = pagerPadding),
                ) { page ->
                    AgentItem(
                        agent = it.agents[page],
                        modifier = Modifier.carouselTransition(page, pagerState),
                        onAgentClick = onAgentClick
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ValorantImage(
                        modifier = Modifier.size(24.dp),
                        imageUrl = it.roleIcon,
                        colorFilter = ColorFilter.tint(ValorantTheme.colors.primary),
                        contentDescription = it.role
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ValorantText(
                        text = it.role,
                        style = ValorantTheme.typography.titleMedium,
                    )
                }
            }
        }
    }
}

@Composable
fun AgentListDesktopContent(
    agents: List<AgentGroupUI>,
    onAgentClick: (String) -> Unit
) {
    LazyColumn {
        items(agents) { agentGroup ->
            Column(
                modifier = Modifier.padding(32.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ValorantImage(
                        modifier = Modifier.size(24.dp),
                        imageUrl = agentGroup.roleIcon,
                        colorFilter = ColorFilter.tint(ValorantTheme.colors.primary),
                        contentDescription = agentGroup.role
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ValorantText(
                        text = agentGroup.role,
                        style = ValorantTheme.typography.titleMedium,
                    )
                }

                LazyVerticalGrid(
                    modifier = Modifier.heightIn(max = 1000.dp),
                    columns = GridCells.Adaptive(240.dp)
                ) {
                    items(agentGroup.agents) { agent ->
                        AgentItem(
                            agent = agent,
                            onAgentClick = onAgentClick
                        )
                    }
                }
            }
        }
    }
}
