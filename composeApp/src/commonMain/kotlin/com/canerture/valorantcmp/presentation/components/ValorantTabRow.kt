package com.canerture.valorantcmp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.canerture.valorantcmp.common.NoRippleInteractionSource
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import kotlinx.coroutines.launch

object ValorantTabRow {
    @Composable
    fun <T> Icon(
        items: List<T>,
        pagerState: PagerState,
        image: (T) -> String,
        contentDescription: (T) -> String,
        iconColors: Pair<Color, Color>,
        bgColors: Pair<Color, Color>,
        pageContent: @Composable (T) -> Unit
    ) {
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            indicator = {},
            divider = {},
        ) {
            items.forEachIndexed { index, item ->
                val textColor = if (pagerState.currentPage == index) iconColors.first else iconColors.second
                val backgroundColor = if (pagerState.currentPage == index) bgColors.first else bgColors.second
                Tab(
                    icon = {
                        ValorantImage(
                            modifier = Modifier.size(32.dp),
                            imageUrl = image(item),
                            contentScale = ContentScale.Fit,
                            colorFilter = ColorFilter.tint(textColor),
                            contentDescription = contentDescription(item)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(12.dp),
                            spotColor = ValorantTheme.colors.primary
                        )
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    interactionSource = NoRippleInteractionSource(),
                )
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        HorizontalPager(
            state = pagerState
        ) { page ->
            pageContent(items[page])
        }
    }

    @Composable
    fun <T> Text(
        items: List<T>,
        pagerState: PagerState,
        text: (T) -> String,
        textColors: Pair<Color, Color>,
        bgColors: Pair<Color, Color>,
        pageContent: @Composable (T) -> Unit
    ) {
        val coroutineScope = rememberCoroutineScope()
        TabRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            indicator = {},
            divider = {},
        ) {
            items.forEachIndexed { index, item ->
                val textColor = if (pagerState.currentPage == index) textColors.first else textColors.second
                val backgroundColor = if (pagerState.currentPage == index) bgColors.first else bgColors.second
                Tab(
                    text = {
                        ValorantText(
                            text = text(item),
                            style = ValorantTheme.typography.titleSmall.copy(color = textColor)
                        )
                    },
                    selected = pagerState.currentPage == index,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(12.dp),
                            spotColor = ValorantTheme.colors.primary
                        )
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    interactionSource = NoRippleInteractionSource(),
                )
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        HorizontalPager(
            state = pagerState
        ) { page ->
            pageContent(items[page])
        }
    }

    @Composable
    fun <T> Scrollable(
        items: List<T>,
        pagerState: PagerState,
        image: (T) -> String,
        contentDescription: (T) -> String,
        bgColors: Pair<Color, Color>,
        pageContent: @Composable (T) -> Unit
    ) {
        val coroutineScope = rememberCoroutineScope()

        ScrollableTabRow(
            containerColor = Color.Transparent,
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp,
            indicator = {},
            divider = {},
        ) {
            items.forEachIndexed { index, item ->
                val backgroundColor = if (pagerState.currentPage == index) bgColors.first else bgColors.second
                Tab(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(12.dp),
                            spotColor = ValorantTheme.colors.primary
                        )
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    interactionSource = NoRippleInteractionSource(),
                    icon = {
                        ValorantImage(
                            modifier = Modifier.size(80.dp, 40.dp).padding(8.dp),
                            imageUrl = image(item),
                            contentScale = ContentScale.Fit,
                            contentDescription = contentDescription(item),
                        )
                    },
                )
            }
        }

        Spacer(modifier = Modifier.size(12.dp))

        HorizontalPager(pagerState) { page ->
            pageContent(items[page])
        }
    }
}
