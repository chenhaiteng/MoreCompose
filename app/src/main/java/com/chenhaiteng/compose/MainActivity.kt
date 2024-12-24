package com.chenhaiteng.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.ui.geometry.fitSquare
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawBlueprint
import androidx.compose.ui.graphics.drawscope.drawRect
import androidx.compose.ui.graphics.rotatedSweepGradient
import androidx.compose.ui.tooling.preview.Preview
import com.chenhaiteng.compose.ui.theme.MoreComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoreComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MoreComposeCanvas(Modifier.fillMaxSize().padding(innerPadding)) {

                    }
                }
            }
        }
    }
}
@Composable
fun MoreComposeGreeting(modifier: Modifier) {
    MoreComposeCanvas(modifier) {
        val rect = size.fitSquare(center)
        drawRect(rect.deflate(20.0f), Color.Red, style = Stroke(width = 5.0f))
        drawRect(rect.deflate(40.0f), Color.White, style = Stroke(width = 10.0f))
    }
}

@Composable
fun MoreComposeCanvas(modifier: Modifier, drawScope: DrawScope.()->Unit) {
    Canvas(modifier = modifier.background(Brush.rotatedSweepGradient(colorStops = arrayOf(
        0.0f to Color.Blue,
        0.1f to Color.Blue.copy(alpha = 0.5f),
        0.2f to Color.Blue,
        0.4f to Color.Blue.copy(alpha = 0.5f),
        0.5f to Color.Blue,
        0.6f to Color.Blue,
        0.8f to Color.Blue.copy(alpha = 0.5f),
        0.9f to Color.Blue,
    ), degree = 0f)), drawScope)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoreComposeCanvas(Modifier.fillMaxSize()) {
        val rect = size.fitSquare(center)
        drawRect(rect, Color.Red, style = Stroke(width = 5.0f))
        drawRect(rect.deflate(40.0f), Color.White, style = Stroke(width = 10.0f))
    }
}