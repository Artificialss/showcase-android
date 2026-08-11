package com.artificialss.showcase.android.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artificialss.showcase.android.R

private val AiGreen = Color(0xFF3DDC84)
private val WoodBrown = Color(0xFF5C3D2E)

/**
 * The original brand logo image, unmodified, with an animated
 * "Powered by AI" sign overlay — the Android counterpart of the
 * web logo's wood-sign animation.
 */
@Composable
fun Logo(modifier: Modifier = Modifier, animated: Boolean = true, size: Dp = 96.dp) {
    Box(modifier = modifier.size(size)) {
        Image(
            painter = painterResource(R.drawable.app_logo),
            contentDescription = stringResource(R.string.logo_content_description),
            modifier = Modifier.size(size),
        )
        if (animated) {
            PoweredByAiBadge(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = size * 0.18f, y = -(size * 0.04f)),
            )
        }
    }
}

@Composable
private fun PoweredByAiBadge(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "sign-swing")
    val angle by infiniteTransition.animateFloat(
        initialValue = -4f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "sign-swing-angle",
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .rotate(angle)
            .clip(RoundedCornerShape(8.dp))
            .background(WoodBrown)
            .padding(horizontal = 14.dp, vertical = 8.dp),
    ) {
        Text(text = "Powered", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(text = "by AI", color = AiGreen, fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }
}
