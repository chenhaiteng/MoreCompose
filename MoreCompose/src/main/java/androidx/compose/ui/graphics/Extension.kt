package androidx.compose.ui.graphics

import androidx.compose.ui.geometry.Offset

fun Matrix.Companion.rotationMatrix(degrees: Float, center: Offset) = Matrix().apply {
    setFrom(androidx.core.graphics.rotationMatrix(degrees, center.x, center.y))
}
