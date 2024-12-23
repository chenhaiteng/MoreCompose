package androidx.compose.ui.graphics.drawscope

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.fitSquare
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin

val Stroke.Companion.blueprint: Stroke
    get() = Stroke(width = 3.0f, cap = StrokeCap.Butt, miter = 3.0f, join = StrokeJoin.Bevel, pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10.0f, 15.0f), 0f))

fun DrawScope.drawBlueprintBaseline(color: Color = Color.Blue, rect: Rect, stroke: Stroke = Stroke(width = 3.0f, cap = StrokeCap.Butt, miter = 3.0f, join = StrokeJoin.Bevel, pathEffect = PathEffect.dashPathEffect(
    floatArrayOf(10.0f, 10.0f), 0f))
) {
    if(color.alpha > 0f) {
        drawLine(color = color, start = rect.centerLeft, end = rect.centerRight, stroke = stroke)
        drawLine(color = color, start = rect.topCenter, end = rect.bottomCenter, stroke = stroke)
    }
}

fun DrawScope.drawBlueprint(color: Color = Color.Blue, alpha: Float = 0.5f, style: Stroke = Stroke.blueprint, insetRatio:Float) {
    val drawingRect = size.fitSquare(center, insetRatio)
    drawRect(rect = size.fitSquare(center), color, alpha, style)
    drawRect(rect = drawingRect, color, alpha, style)
    drawLine(color, alpha = alpha, start = drawingRect.centerLeft, end = drawingRect.centerRight, stroke = style)
    drawLine(color, alpha = alpha, start = drawingRect.topCenter, end = drawingRect.bottomCenter, stroke = style)
}

fun DrawScope.drawTextBlueprint(textBound: Rect, offset: Offset, baseline: Float, color: Color = Color.Blue, alpha: Float = 0.5f, style: Stroke = Stroke.blueprint) {
    val rect = textBound.translate(offset)
    drawRect(
        rect = rect,
        color = color,
        alpha = alpha,
        style = style
    )
    // Show Baseline
    drawLine(
        Color.DarkGray,
        stroke = style,
        alpha = alpha,
        start = Offset(rect.left, offset.y + baseline),
        end = Offset(rect.right, offset.y + baseline)
    )
}