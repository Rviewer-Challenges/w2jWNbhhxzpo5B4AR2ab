package com.p4r4d0x.hollowminds.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Colors = lightColors(
    primary = Grey,
    primaryVariant = GreyDark,
    onPrimary = Color.White,
    secondary = GreyLight,
    onSecondary = GreyLightLight,
    background = Color.White,
    onBackground = Color.Black,
    surface = GreyDarkTransparent,
    onSurface = Color.Black,
    error = Red,
    onError = Color.White
)

@Composable
fun HollowMindsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = Colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
