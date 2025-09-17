package ru.mareanexx.feature_translate.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun popUpScaleValue(
    active: Boolean,
    initial: Float = 1f,
    peak: Float = 1.2f,
    settle: Float = 1.05f,
    upDuration: Int = 100,
    downDuration: Int = 100
): Float {
    val scale = remember { Animatable(initial) }

    LaunchedEffect(active) {
        if (active) {
            scale.snapTo(initial)
            scale.animateTo(
                targetValue = peak,
                animationSpec = tween(durationMillis = upDuration, easing = LinearOutSlowInEasing)
            )
            scale.animateTo(
                targetValue = settle,
                animationSpec = tween(durationMillis = downDuration, easing = FastOutLinearInEasing)
            )
        }
//        else {
//            scale.animateTo(
//                targetValue = initial,
//                animationSpec = tween(durationMillis = downDuration)
//            )
//        }
    }

    return scale.value
}