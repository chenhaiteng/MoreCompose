package androidx.compose.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationResult
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.roundToInt

suspend
fun Animatable<Float, AnimationVector1D>.backAndForth(durationMillis: Int = 3000,
                                                      toValue: Float = 1.0f,
                                                      block: (Animatable<Float, AnimationVector1D>.() -> Unit)? = null): AnimationResult<Float, AnimationVector1D> {
    return animateTo(
        targetValue = toValue,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        block = block
    )
}

suspend
fun Animatable<Float, AnimationVector1D>.infiniteRotate(durationMillis: Int,
                                                        clockwise: Boolean = false,
                                                        block: (Animatable<Float, AnimationVector1D>.() -> Unit)? = null): AnimationResult<Float, AnimationVector1D> {
    return animateTo(
        targetValue = if(clockwise) {this.value + 360f} else {this.value - 360f},
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        block = block
    )
}

@Composable
inline fun LaunchInfRotation(duration: Float, rotation: Animatable<Float, AnimationVector1D>, crossinline block: Float.() -> Unit) = LaunchedEffect(key1 = duration) {
    if(duration > 0f) {
        rotation.infiniteRotate((duration.roundToInt() * 1000)) {
            value.block()
        }
    }
}

@Composable
inline fun ResettableRotate(start: Boolean = true,
                            animatable: Animatable<Float, AnimationVector1D>,
                            duration: Float,
                            crossinline block: Float.()->Float) {
    var stopPoint by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(start, duration) {
        if (start) {
            animatable.infiniteRotate((duration.roundToInt() * 1000)) {
                (value - stopPoint).block()
            }
        } else {
            stopPoint = Float.NaN.block() + animatable.value
        }
    }
}

@Composable
fun RotateBackgroundBox(modifier: Modifier, rotationDuration: Float = 50f, background: DrawScope.(degree: Float) -> Unit, content: @Composable BoxScope.() -> Unit) {
    var backgroundValue by remember { mutableFloatStateOf(0f) }
    val backgroundRotation  = remember {
        Animatable(backgroundValue)
    }
    LaunchInfRotation(duration = rotationDuration, rotation = backgroundRotation) {
        backgroundValue = this
    }
    Box(modifier = modifier.drawBehind {
        background(backgroundValue)
    }, content = content)
}
@Composable
fun RotateBackgroundBox(modifier: Modifier, rotationDuration: Float = 50f, background: DrawScope.(degree: Float) -> Unit) {
    RotateBackgroundBox(modifier = modifier, rotationDuration = rotationDuration, background = background) {
    }
}

fun Float.updateValue(default: Float, block:(Float)->Unit) : Float {
    if(this.isNaN()) {
        block(default)
        return -default
    } else {
        block(this)
        return this
    }
}
