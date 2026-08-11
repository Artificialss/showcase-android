package com.artificialss.showcase.android.ui.feature.portfolio

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

private val AndroidGreen = Color(0xFF3DDC84)
private val NextBlack = Color(0xFF0A0A0A)
private val PapasarPurple = Color(0xFF7C3AED)
private val ArtificialssTeal = Color(0xFF347E67)
private val KotlinGreenDark = Color(0xFF123A26)

@Composable
fun PortfolioMockup(id: String, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.fillMaxSize()) {
        when (id) {
            "showcase-android" -> drawAndroidMockup()
            "showcase-cmm" -> drawCmmMockup()
            "showcase-nextjs" -> drawNextjsMockup()
            "papasar" -> drawPapasarMockup()
            else -> drawArtificialssMockup()
        }
    }
}

private fun DrawScope.drawAndroidMockup() {
    drawRect(Color(0xFF0D1912))
    // Phone frame
    val frameWidth = size.width * 0.34f
    val frameLeft = (size.width - frameWidth) / 2
    val frameTop = size.height * 0.08f
    val frameHeight = size.height * 0.84f
    drawRoundRect(
        color = Color(0xFF123A26),
        topLeft = Offset(frameLeft, frameTop),
        size = Size(frameWidth, frameHeight),
        cornerRadius = CornerRadius(24f, 24f),
    )
    // Screen content: a couple of "cards"
    val padding = frameWidth * 0.12f
    drawRoundRect(
        color = AndroidGreen.copy(alpha = 0.3f),
        topLeft = Offset(frameLeft + padding, frameTop + padding * 1.5f),
        size = Size(frameWidth - padding * 2, frameHeight * 0.18f),
        cornerRadius = CornerRadius(8f, 8f),
    )
    drawRoundRect(
        color = AndroidGreen.copy(alpha = 0.2f),
        topLeft = Offset(frameLeft + padding, frameTop + frameHeight * 0.32f),
        size = Size(frameWidth - padding * 2, frameHeight * 0.28f),
        cornerRadius = CornerRadius(8f, 8f),
    )
    // Bottom nav bar with 2 dots (icons)
    val navTop = frameTop + frameHeight * 0.82f
    drawRoundRect(
        color = Color(0xFF0A2A1B),
        topLeft = Offset(frameLeft, navTop),
        size = Size(frameWidth, frameHeight * 0.18f),
        cornerRadius = CornerRadius(0f, 0f),
    )
    val navCenterY = navTop + frameHeight * 0.09f
    drawCircle(AndroidGreen, radius = 6f, center = Offset(frameLeft + frameWidth * 0.35f, navCenterY))
    drawCircle(AndroidGreen.copy(alpha = 0.4f), radius = 6f, center = Offset(frameLeft + frameWidth * 0.65f, navCenterY))
}

private fun DrawScope.drawCmmMockup() {
    drawRect(KotlinGreenDark.copy(alpha = 0.9f))
    drawRect(Color(0xFF0D1912))
    drawRoundRect(
        color = Color(0xFF123A26),
        topLeft = Offset(0f, 0f),
        size = Size(size.width, size.height * 0.16f),
    )
    val cardWidth = size.width * 0.42f
    drawRoundRect(
        color = Color(0xFF123A26),
        topLeft = Offset(size.width * 0.05f, size.height * 0.25f),
        size = Size(cardWidth, size.height * 0.3f),
        cornerRadius = CornerRadius(10f, 10f),
    )
    drawRoundRect(
        color = Color(0xFF123A26),
        topLeft = Offset(size.width * 0.53f, size.height * 0.25f),
        size = Size(cardWidth, size.height * 0.3f),
        cornerRadius = CornerRadius(10f, 10f),
    )
    val barHeights = listOf(0.3f, 0.45f, 0.25f, 0.55f, 0.4f, 0.5f)
    val barWidth = size.width * 0.1f
    barHeights.forEachIndexed { i, h ->
        val barHeight = size.height * 0.25f * h
        drawRoundRect(
            color = AndroidGreen.copy(alpha = 0.5f + i * 0.06f),
            topLeft = Offset(size.width * 0.08f + i * barWidth * 1.15f, size.height * 0.82f - barHeight),
            size = Size(barWidth, barHeight),
            cornerRadius = CornerRadius(3f, 3f),
        )
    }
}

private fun DrawScope.drawNextjsMockup() {
    drawRect(NextBlack)
    drawRect(Color(0xFF161616), size = Size(size.width, size.height * 0.14f))
    listOf(Color(0xFFFF5F57), Color(0xFFFEBC2E), Color(0xFF28C840)).forEachIndexed { i, c ->
        drawCircle(c, radius = size.width * 0.02f, center = Offset(size.width * (0.08f + i * 0.07f), size.height * 0.07f))
    }
    drawCircle(
        AndroidGreen,
        radius = size.width * 0.12f,
        center = Offset(size.width * 0.5f, size.height * 0.5f),
        alpha = 0.85f,
    )
    drawRoundRect(
        color = Color.White.copy(alpha = 0.8f),
        topLeft = Offset(size.width * 0.3f, size.height * 0.78f),
        size = Size(size.width * 0.4f, size.height * 0.05f),
        cornerRadius = CornerRadius(4f, 4f),
    )
}

private fun DrawScope.drawPapasarMockup() {
    drawRect(Color(0xFF1A1035))
    drawRect(Color(0xFF2D1F5E), size = Size(size.width, size.height * 0.18f))
    val rowHeight = size.height * 0.13f
    listOf(0.35f, 0.55f, 0.65f, 0.45f).forEachIndexed { i, w ->
        val y = size.height * 0.3f + i * rowHeight * 1.1f
        val selected = i == 1
        drawRoundRect(
            color = if (selected) PapasarPurple else Color(0xFF2D1F5E),
            topLeft = Offset(size.width * 0.06f, y),
            size = Size(size.width * 0.88f, rowHeight * 0.7f),
            cornerRadius = CornerRadius(8f, 8f),
            alpha = if (selected) 0.9f else 0.5f,
        )
    }
}

private fun DrawScope.drawArtificialssMockup() {
    drawRect(Color(0xFF0C0F14))
    drawRect(Color(0xFF111820), size = Size(size.width, size.height * 0.16f))
    drawCircle(ArtificialssTeal, radius = size.width * 0.03f, center = Offset(size.width * 0.08f, size.height * 0.08f))
    drawRoundRect(
        color = Color(0xFFE2E8F0).copy(alpha = 0.7f),
        topLeft = Offset(size.width * 0.08f, size.height * 0.25f),
        size = Size(size.width * 0.5f, size.height * 0.06f),
        cornerRadius = CornerRadius(4f, 4f),
    )
    val cardWidth = size.width * 0.26f
    (0..2).forEach { i ->
        drawRoundRect(
            color = Color(0xFF151C25),
            topLeft = Offset(size.width * 0.08f + i * cardWidth * 1.05f, size.height * 0.5f),
            size = Size(cardWidth, size.height * 0.35f),
            cornerRadius = CornerRadius(8f, 8f),
        )
    }
}
