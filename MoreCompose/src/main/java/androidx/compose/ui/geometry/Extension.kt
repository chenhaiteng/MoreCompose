package androidx.compose.ui.geometry

import androidx.compose.ui.graphics.Path
import kotlin.math.min

fun Rect.fitSquare(): Rect {
    val radius = min(size.width, size.height)/2f
    return Rect(center, radius)
}

fun Size.fitSquare(center: Offset, insetRatio: Float = 0f): Rect {
    val radius = min(width, height)/2f*(1f - insetRatio)
    return Rect(center, radius)
}

// Path Extension
fun Path.moveTo(offset: Offset) = moveTo(offset.x, offset.y)

fun Path.lineTo(offset: Offset) = lineTo(offset.x, offset.y)

fun Path.cubicTo(control1: Offset, control2: Offset, point: Offset) = cubicTo(control1.x, control1.y, control2.x, control2.y, point.x, point.y)

// Offset Extension
fun Offset.minus(x: Float = 0f, y: Float = 0f): Offset = minus(Offset(x, y))
fun Offset.plus(x: Float = 0f, y: Float = 0f): Offset = plus(Offset(x, y))