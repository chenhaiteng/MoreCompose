package androidx.compose.ui.graphics

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.geometry.isUnspecified
import androidx.core.graphics.rotationMatrix


@Stable
fun Brush.Companion.rotatedSweepGradient(
    vararg colorStops: Pair<Float, Color>,
    center: Offset = Offset.Unspecified,
    degree: Float
): Brush = RotatedSweepGradient(
    colors = List<Color>(colorStops.size) { i -> colorStops[i].second },
    stops = List<Float>(colorStops.size) { i -> colorStops[i].first },
    center = center,
    degree = degree
)

@Immutable
class RotatedSweepGradient internal constructor(
    private val center: Offset,
    private val degree: Float,
    private val colors: List<Color>,
    private val stops: List<Float>? = null
) : ShaderBrush() {

    override fun createShader(size: Size): Shader {
        val c = if (center.isUnspecified) { size.center } else {
            Offset(
                if (center.x == Float.POSITIVE_INFINITY) size.width else center.x,
                if (center.y == Float.POSITIVE_INFINITY) size.height else center.y
            )
        }
        val shader = SweepGradientShader(
            c,
            colors,
            stops
        )
        shader.setLocalMatrix(rotationMatrix(degrees = degree, c.x, c.y))
        return shader
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RotatedSweepGradient) return false

        if (center != other.center) return false
        if (colors != other.colors) return false
        if (stops != other.stops) return false
        if (degree != other.degree) return false

        return true
    }

    override fun hashCode(): Int {
        var result = center.hashCode()
        result = 31 * result + colors.hashCode()
        result = 31 * result + (stops?.hashCode() ?: 0)
        result = 31 * result + degree.hashCode()
        return result
    }

    override fun toString(): String {
        val centerValue = if (center.isSpecified) "center=$center, " else ""
        return "RotatedSweepGradient(" +
                centerValue +
                "colors=$colors, stops=$stops, degree=$degree)"
    }
}