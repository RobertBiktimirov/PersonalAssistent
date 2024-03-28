package dev.susu.personalassistant.core.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


private val TopGradientColor: Color = Color(0xFFFDFDFE)
private val CenterGradientColor = Color(0xFFF2F4F9)
private val BottomGradientColor = Color(0xFFD7DAE6)

val BackgroundGradient =
    Brush.verticalGradient(
        colors = listOf(
            TopGradientColor,
            CenterGradientColor,
            BottomGradientColor
        )
    )

val GreyTextColor: Color = Color(0xFF6E7591)
val YellowColor = Color(0xFFFFC107)
val LineBackground = Color(0xFFDCE1EF)
val TaskItemColor = Color(0xFFEEF6FE)

val String.parseColor: Color
    get() {
        require(this.length == 7 && this[0] == '#') {
            "Invalid HEX color format"
        }

        val red = this.substring(1, 3).toInt(16) / 255f
        val green = this.substring(3, 5).toInt(16) / 255f
        val blue = this.substring(5, 7).toInt(16) / 255f

        return Color(red, green, blue, alpha = 1f)
    }
