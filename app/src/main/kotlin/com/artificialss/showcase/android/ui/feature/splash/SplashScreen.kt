package com.artificialss.showcase.android.ui.feature.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artificialss.showcase.android.R
import kotlinx.coroutines.delay

private val BrandGreen = Color(0xFF347E67)
private const val SPLASH_DELAY_MS = 2000L
private const val FADE_DURATION_MS = 800
private const val SUBTITLE_ALPHA = 0.8f
private val SPACING = 8.dp
private val LOGO_SPACING = 24.dp
private const val LOGO_WIDTH_FRACTION = 0.5f
private const val BRAND_NAME = "Artificialss"
private const val BRAND_TAGLINE = "Showcase"

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit, modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = FADE_DURATION_MS),
        label = "splash_alpha",
    )

    LaunchedEffect(Unit) {
        visible = true
        delay(SPLASH_DELAY_MS)
        onNavigateToHome()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BrandGreen),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.artificial_fixed),
                contentDescription = stringResource(R.string.logo_content_description),
                modifier = Modifier
                    .fillMaxWidth(LOGO_WIDTH_FRACTION)
                    .alpha(alpha),
            )
            Spacer(modifier = Modifier.height(LOGO_SPACING))
            Text(
                text = BRAND_NAME,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alpha(alpha),
            )
            Spacer(modifier = Modifier.height(SPACING))
            Text(
                text = BRAND_TAGLINE,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White.copy(alpha = SUBTITLE_ALPHA),
                modifier = Modifier.alpha(alpha),
            )
        }
    }
}
