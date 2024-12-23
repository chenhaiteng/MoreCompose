package androidx.compose.ui.graphics.drawscope

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter

fun DrawScope.drawLine(color: Color,
                       start: Offset,
                       end: Offset,
                       stroke: Stroke,
                       /*FloatRange(from = 0.0, to = 1.0)*/
                       alpha: Float = 1.0f,
                       colorFilter: ColorFilter? = null,
                       blendMode: BlendMode = DrawScope.DefaultBlendMode
) = drawLine(color, start, end, stroke.width, stroke.cap, stroke.pathEffect, alpha, colorFilter, blendMode)
