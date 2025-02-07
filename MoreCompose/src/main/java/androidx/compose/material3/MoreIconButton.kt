/* 
 * MoreIconButton.kt
 * Created by Chen Hai Teng at 2/6/25
 * Copyright (c) 2025 Chen Hai Teng. All rights reserved.
 */
package androidx.compose.material3

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun LongClickableWrapper(onClick: ()->Unit, onLongClick: ()->Unit, block: @Composable (InteractionSource)-> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val viewConfiguration = LocalViewConfiguration.current
    LaunchedEffect(interactionSource) {
        var isLongClick = false
        interactionSource.interactions.collectLatest { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    isLongClick = false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    isLongClick = true
                }
                is PressInteraction.Release -> {
                    if (isLongClick) {
                        onLongClick()
                    } else {
                        onClick()
                    }
                }
            }
        }
    }
    block(interactionSource)
}

@Composable
fun MoreIconButton(modifier: Modifier = Modifier.size(48.dp), @DrawableRes iconRes: Int, contentDescription: String = "icon button of more compose", tint:Color = Color.White, onLongClick: () -> Unit = {}, onClick: ()->Unit) {
    LongClickableWrapper(onClick, onLongClick) { interactionSource ->
        IconButton(
            modifier = modifier,
            onClick = { },
            interactionSource = interactionSource as MutableInteractionSource?
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                modifier = Modifier.size(28.dp),
                tint = tint
            )
        }
    }
}

@Composable
fun MoreIconButton(modifier: Modifier = Modifier.size(48.dp), imageVector: ImageVector, contentDescription: String = "icon button of more compose", tint:Color = Color.White, onLongClick: () -> Unit = {}, onClick: ()->Unit) {
    LongClickableWrapper(onClick, onLongClick) { interactionSource ->
        IconButton(
            modifier = modifier,
            onClick = { },
            interactionSource = interactionSource as MutableInteractionSource?
        ) {
            Icon(
                imageVector,
                contentDescription = contentDescription,
                modifier = Modifier.size(28.dp),
                tint = tint
            )
        }
    }
}
