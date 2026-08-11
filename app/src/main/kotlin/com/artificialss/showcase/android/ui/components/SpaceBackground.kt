package com.artificialss.showcase.android.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

private val SpaceBlack = Color(0xFF05070D)

private data class Star(val x: Float, val y: Float, val radius: Float, val phaseOffset: Float)

private const val STAR_COUNT = 90

/**
 * Full-bleed black sky with gently twinkling stars — sits behind the
 * Portfolio screen's content. The moon is a separate overlay (see
 * [MoonBadge]) so it can render above the bottom navigation bar.
 */
@Composable
fun SpaceBackground(modifier: Modifier = Modifier) {
    val stars = remember {
        val random = Random(seed = 7)
        List(STAR_COUNT) {
            Star(
                x = random.nextFloat(),
                y = random.nextFloat(),
                radius = random.nextFloat() * 1.8f + 0.6f,
                phaseOffset = random.nextFloat() * 2f * PI.toFloat(),
            )
        }
    }

    val infiniteTransition = rememberInfiniteTransition(label = "space-twinkle")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "space-twinkle-time",
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        drawRect(SpaceBlack)

        stars.forEach { star ->
            val twinkle = (sin(time + star.phaseOffset) + 1f) / 2f
            drawCircle(
                color = Color.White.copy(alpha = 0.25f + twinkle * 0.65f),
                radius = star.radius,
                center = Offset(star.x * size.width, star.y * size.height),
            )
        }
    }
}
