package com.artificialss.showcase.android.ui.feature.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artificialss.showcase.android.R
import com.artificialss.showcase.android.domain.model.PortfolioItem
import com.artificialss.showcase.android.ui.components.MoonBadge
import com.artificialss.showcase.android.ui.components.SpaceBackground
import com.artificialss.showcase.android.ui.components.openUrl
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.koin.androidx.compose.koinViewModel

private const val AUTO_ADVANCE_DELAY_MS = 2000L
private val White70 = Color.White.copy(alpha = 0.7f)
private val CardSurface = Color(0xFF13172A)

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        SpaceBackground(modifier = Modifier.fillMaxSize())

        MoonBadge(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 20.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars),
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(R.string.portfolio_badge),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = stringResource(R.string.made_by_artificialss),
                        style = MaterialTheme.typography.labelSmall,
                        color = White70,
                    )
                }
                Text(
                    text = stringResource(R.string.portfolio_title),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp),
                )
                Text(
                    text = stringResource(R.string.portfolio_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = White70,
                    modifier = Modifier.padding(top = 4.dp),
                )
            }

            when (val state = uiState) {
                is PortfolioUiState.Loading -> Unit
                is PortfolioUiState.Success -> PortfolioPager(items = state.items)
            }
        }
    }
}

@Composable
private fun PortfolioPager(items: List<PortfolioItem>) {
    val pagerState = rememberPagerState(pageCount = { items.size })
    val context = LocalContext.current

    LaunchedEffect(pagerState, items.size) {
        if (items.size <= 1) return@LaunchedEffect
        while (isActive) {
            delay(AUTO_ADVANCE_DELAY_MS)
            val next = (pagerState.currentPage + 1) % items.size
            pagerState.animateScrollToPage(next)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        pageSpacing = 12.dp,
    ) { page ->
        val item = items[page]
        PortfolioCard(
            item = item,
            modifier = Modifier
                .padding(16.dp)
                .clickable { context.openUrl(item.url) },
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(items.size) { index ->
            val selected = index == pagerState.currentPage
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (selected) 8.dp else 6.dp)
                    .clip(CircleShape)
                    .background(if (selected) Color.White else Color.White.copy(alpha = 0.35f)),
            )
        }
    }
}

@Composable
private fun PortfolioCard(item: PortfolioItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurface),
    ) {
        PortfolioMockup(
            id = item.id,
            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f),
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = White70,
                modifier = Modifier.padding(top = 4.dp),
            )
            LazyRow(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                items(item.tags) { tag ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.White.copy(alpha = 0.12f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text(text = tag, style = MaterialTheme.typography.labelSmall, color = White70)
                    }
                }
            }
            Text(
                text = stringResource(R.string.portfolio_view_project),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}
