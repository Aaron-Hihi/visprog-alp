package com.aaron.walkcore.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

//val Purple80 = Color(0xFFD0BCFF)
//val PurpleGrey80 = Color(0xFFCCC2DC)
//val Pink80 = Color(0xFFEFB8C8)
//
//val Purple40 = Color(0xFF6650a4)
//val PurpleGrey40 = Color(0xFF625b71)
//val Pink40 = Color(0xFF7D5260)

// Colors
val White = Color(0xFFFFFFFF)
val LightGrey = Color(0xFFD9D9D9)
val DarkGrey = Color(0xFF5A5A5A)
val DarkGrey2 = Color(0xFF1E1E1E)
val Black = Color(0xFF000000)
val Blue = Color(0xFF54A4FF)
val Green = Color(0xFF54FF8D)
val Yellow = Color(0xFFEEFF54)

// Brushes
val BlueToGreen = Brush.linearGradient(
    colors = listOf(
        Blue,
        Green
    )
)

val BlueToYellow = Brush.linearGradient(
    colors = listOf(
        Blue,
        Yellow
    )
)

val YellowToGreen = Brush.linearGradient(
    colors = listOf(
        Yellow,
        Green
    )
)

val YellowToBlue = Brush.linearGradient(
    colors = listOf(
        Yellow,
        Blue
    )
)

val GreenToBlue = Brush.linearGradient(
    colors = listOf(
        Green,
        Blue
    )
)

val GreenToYellow = Brush.linearGradient(
    colors = listOf(
        Green,
        Yellow
    )
)