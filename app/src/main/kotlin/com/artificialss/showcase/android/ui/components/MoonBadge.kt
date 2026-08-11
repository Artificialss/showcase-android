package com.artificialss.showcase.android.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val MOON_SIZE = 60.dp
private val GLOW_SIZE = 100.dp

/**
 * A full moon with a soft pulsing glow, meant to be placed as part of the
 * Portfolio screen's own content (so it's naturally clipped above the
 * bottom navigation bar, not a special root-level overlay).
 */
@Composable
fun MoonBadge(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "moon-glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.12f,
        targetValue = 0.30f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "moon-glow-alpha",
    )

    Canvas(modifier = modifier.size(GLOW_SIZE)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val moonRadius = MOON_SIZE.toPx() / 2f

        drawCircle(color = Color.White.copy(alpha = glowAlpha * 0.5f), radius = moonRadius * 1.6f, center = center)
        drawCircle(color = Color.White.copy(alpha = glowAlpha), radius = moonRadius * 1.25f, center = center)
        drawCircle(color = Color.White, radius = moonRadius, center = center)
    }
}
