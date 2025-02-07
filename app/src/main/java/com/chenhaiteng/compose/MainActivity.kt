package com.chenhaiteng.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MoreIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.cubicTo
import androidx.compose.ui.geometry.fitSquare
import androidx.compose.ui.geometry.lineTo
import androidx.compose.ui.geometry.minus
import androidx.compose.ui.geometry.moveTo
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawRect
import androidx.compose.ui.graphics.drawscope.drawText
import androidx.compose.ui.graphics.rotatedSweepGradient
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chenhaiteng.compose.ui.theme.MoreComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoreComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    BottomAppBar {
                        MoreIconButton(imageVector = Icons.Filled.Settings, onLongClick = {
                            println("long click")
                        }) {
                            println("click")
                        }
                    }
                }) { innerPadding ->
                    MoreComposeCanvas(Modifier.fillMaxSize().padding(innerPadding)) {

                    }
                }
            }
        }
    }
}

fun Brush.Companion.colorRing(degree: Float) = Brush.rotatedSweepGradient(colorStops = arrayOf(
    0.0f to Color.Red,
    0.333f to Color.Green,
    0.666f to Color.Blue,
    1.0f to Color.Red
), degree = degree)

@Composable
fun MoreComposeGreeting(modifier: Modifier, greeting: String) {
    val measurer = rememberTextMeasurer()
    val fontSize = 30.sp
    val layout = measurer.measure(greeting, style = TextStyle.Default.copy(fontSize = fontSize, color = Color.Cyan))
    MoreComposeCanvas(modifier) {
        val rect = size.fitSquare(center)
        val textTopLeft = rect.topLeft.plus(Offset(x = 50f, y = 50f))
        drawRect(rect.deflate(20.0f), Color.Red, style = Stroke(width = 5.0f))
        drawRect(rect.deflate(40.0f), Color.White, style = Stroke(width = 10.0f))
        drawText(layout, topLeft = textTopLeft, bluePrintColor = Color.White)
        drawText(layout, topLeft = textTopLeft.plus(Offset.Zero.copy(y = fontSize.toPx() + 10f)), brush = Brush.colorRing(degree = 0f))
        val p = Path().apply {
            moveTo(rect.center)
            lineTo(rect.bottomRight)
            cubicTo(rect.bottomRight.minus(y = 160f), rect.bottomLeft.minus(y = 160f), rect.bottomLeft)
            lineTo(rect.center)
        }
        drawPath(path = p, color = Color.Yellow, style = Stroke(width = 2.0f))
    }
}

val blueBrush = Brush.rotatedSweepGradient(colorStops = arrayOf(
    0.0f to Color.Blue,
    0.1f to Color.Blue.copy(alpha = 0.5f),
    0.2f to Color.Blue,
    0.4f to Color.Blue.copy(alpha = 0.5f),
    0.5f to Color.Blue,
    0.6f to Color.Blue,
    0.8f to Color.Blue.copy(alpha = 0.5f),
    0.9f to Color.Blue,
), degree = 0f)

@Composable
fun MoreComposeCanvas(modifier: Modifier, drawScope: DrawScope.()->Unit) {
    Canvas(modifier = modifier.background(blueBrush), drawScope)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoreComposeGreeting(Modifier.fillMaxSize(), greeting = "Hello!! MoreCompose!")
}