package dev.susu.personalassistant.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


private val TopGradientColor = Color(0xFFFDFDFE)
private val CenterGradientColor = Color(0xFFF2F4F9)
private val BottomGradientColor = Color(0xFFD7DAE6)

val BackgroundGradient =
    Brush.verticalGradient(colors = listOf(TopGradientColor, CenterGradientColor, BottomGradientColor))

val GreyTextColor = Color(0xFF6E7591)
