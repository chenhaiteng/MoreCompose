package androidx.compose.ui.text

import android.graphics.RectF
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.toAndroidRectF
import androidx.compose.ui.graphics.toComposeRect
import androidx.core.graphics.toRect

fun TextLayoutResult.fullBoundingBox() : Rect {
    val fullBoundingBox = RectF(Float.MAX_VALUE, Float.MAX_VALUE, Float.MIN_VALUE, Float.MIN_VALUE)
    for(i in 0..<layoutInput.text.text.length) {
        val rect = getBoundingBox(i).toAndroidRectF()
        fullBoundingBox.union(rect)
    }
    return fullBoundingBox.toRect().toComposeRect()
}