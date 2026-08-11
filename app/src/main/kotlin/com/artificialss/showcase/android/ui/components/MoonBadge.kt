package com.artificialss.showcase.android.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val MOON_SIZE = 60.dp
private val GLOW_SIZE = 100.dp

/**
 * A full moon icon with a soft pulsing glow, meant to be placed in a
 * root-level overlay (outside the Scaffold's content slot) so it renders on
 * top of the bottom navigation bar instead of being clipped by the content
 * area — a UI element, not part of the space background.
 */
@Composable
fun MoonBadge(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "moon-glow")
    val glowScale by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "moon-glow-scale",
    )
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.25f,
        targetValue = 0.55f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "moon-glow-alpha",
    )

    Box(modifier = modifier.size(GLOW_SIZE), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(GLOW_SIZE)
                .scale(glowScale)
                .blur(24.dp)
                .background(Color.White.copy(alpha = glowAlpha), CircleShape),
        )
        Icon(
            imageVector = Icons.Filled.Circle,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(MOON_SIZE),
        )
    }
}
