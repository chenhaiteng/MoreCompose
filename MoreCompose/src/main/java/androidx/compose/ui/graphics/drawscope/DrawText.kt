package androidx.compose.ui.graphics.drawscope

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextDecoration

fun DrawScope.drawText(
    textLayoutResult: TextLayoutResult,
    color: Color = Color.Unspecified,
    topLeft: Offset = Offset.Zero,
    alpha: Float = Float.NaN,
    shadow: Shadow? = null,
    textDecoration: TextDecoration? = null,
    drawStyle: DrawStyle? = null,
    blendMode: BlendMode = DrawScope.DefaultBlendMode,
    bluePrintColor: Color = Color.Transparent,
    bluePrintStyle: DrawStyle = Stroke(width = 3.0f, cap = StrokeCap.Butt, miter = 3.0f, join = StrokeJoin.Bevel, pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10.0f, 10.0f), 0f))) {
    if (bluePrintColor.alpha > 0f) {
        drawRect(
            rect = Rect(topLeft, bottomRight = topLeft.plus(Offset(textLayoutResult.size.width.toFloat(), textLayoutResult.size.height.toFloat()))),
            color = bluePrintColor,
            style = bluePrintStyle
        )
    }
    drawText(textLayoutResult,color,topLeft,alpha,shadow,textDecoration,drawStyle,blendMode)
}
fun DrawScope.drawText(
    textLayoutResult: TextLayoutResult,
    brush: Brush,
    topLeft: Offset = Offset.Zero,
    alpha: Float = Float.NaN,
    shadow: Shadow? = null,
    textDecoration: TextDecoration? = null,
    drawStyle: DrawStyle? = null,
    blendMode: BlendMode = DrawScope.DefaultBlendMode,
    bluePrintColor: Color = Color.Transparent,
    bluePrintStyle: DrawStyle = Stroke(width = 3.0f, cap = StrokeCap.Butt, miter = 3.0f, join = StrokeJoin.Bevel, pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10.0f, 10.0f), 0f))) {
    if (bluePrintColor.alpha > 0f) {
        drawRect(
            rect = Rect(topLeft, bottomRight = topLeft.plus(Offset(textLayoutResult.size.width.toFloat(), textLayoutResult.size.height.toFloat()))),
            color = bluePrintColor,
            style = bluePrintStyle
        )
    }
    drawText(textLayoutResult,brush,topLeft,alpha,shadow,textDecoration,drawStyle,blendMode)
}