package androidx.compose.ui.graphics.drawscope

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

fun DrawScope.drawRect(rect: Rect,
                       color: Color,
                       alpha: Float = 1.0f,
                       style: DrawStyle = Fill,
                       colorFilter: ColorFilter? = null,
                       blendMode: BlendMode = DrawScope.DefaultBlendMode
) = drawRect(color, rect.topLeft, rect.size, alpha, style, colorFilter, blendMode)