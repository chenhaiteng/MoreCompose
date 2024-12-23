package androidx.compose.ui.graphics

import android.graphics.ComposeShader
import android.graphics.PorterDuff
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.isUnspecified
import androidx.core.graphics.rotationMatrix

@Stable
fun Brush.Companion.glowingGradient(
    sweepColors: List<Pair<Float, Color>>,
    radialColors: List<Pair<Float, Color>>,
    center: Offset = Offset.Unspecified,
    degrees: Float = 0f,
    radius: Float
): Brush = GlowingRadialBrush(center,radius,degrees,sweepColors,radialColors)

class GlowingRadialBrush internal constructor(
    private val center: Offset,
    private val radius: Float,
    private val degrees: Float = 0f,
    private val sweepColors: List<Pair<Float, Color>>,
    private val radialColors: List<Pair<Float, Color>>,
    private val tileMode: TileMode = TileMode.Clamp
) : ShaderBrush()  {
    override fun createShader(size: Size): Shader {
        val c = if(center.isUnspecified) { size.center } else {
            Offset(
                if (center.x == Float.POSITIVE_INFINITY) size.width else center.x,
                if (center.y == Float.POSITIVE_INFINITY) size.height else center.y
            )
        }
        val sweepshader = SweepGradientShader(
            c,
            List<Color>(sweepColors.size) { i -> sweepColors[i].second },
            List<Float>(sweepColors.size) { i -> sweepColors[i].first }
        )
        sweepshader.setLocalMatrix(rotationMatrix(degrees = degrees, c.x, c.y))
        val radialshader = RadialGradientShader(
            c,
            radius,
            List<Color>(radialColors.size) { i -> radialColors[i].second },
            List<Float>(radialColors.size) { i -> radialColors[i].first },
            tileMode)
        return ComposeShader(sweepshader, radialshader, PorterDuff.Mode.SRC_OVER)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GlowingRadialBrush) return false
        if (tileMode != other.tileMode) return false
        if (center != other.center) return false
        if (sweepColors != other.sweepColors) return false
        if (radialColors != other.radialColors) return false
        if (radius != other.radius) return false
        if (degrees != other.degrees) return false

        return true
    }

    override fun hashCode(): Int {
        var result = center.hashCode()
        result = 31 * result + sweepColors.hashCode()
        result = 31 * result + radialColors.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + tileMode.hashCode()
        result = 31 * result + degrees.hashCode()
        return result
    }
}