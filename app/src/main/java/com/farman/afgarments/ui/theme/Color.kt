package com.farman.afgarments.ui.theme

import androidx.compose.ui.graphics.Color

// Base colors
val LightBackground = Color(0xFFF2F4F8)
val LightSurface = Color(0xFFFFFFFF)
val LightTextPrimary = Color(0xFF111827)
val LightTextSecondary = Color(0xFF6B7280)
val LightBorder = Color(0xFFE5E7EB)

val DarkBackground = Color(0xFF0F1117)
val DarkSurface = Color(0xFF1C1F26)
val DarkTextPrimary = Color(0xFFF9FAFB)
val DarkTextSecondary = Color(0xFF9CA3AF)
val DarkBorder = Color(0xFF2D3139)

val PosGreen = Color(0xFF059669)
val NegRed = Color(0xFFDC2626)

// Accents (Primary & Gradient End)
val RosePrimary = Color(0xFFE11D48)
val RoseDark = Color(0xFF9F1239)

val BluePrimary = Color(0xFF2563EB)
val BlueDark = Color(0xFF1D4ED8)

val GreenPrimary = Color(0xFF059669)
val GreenDark = Color(0xFF065F46)

val OrangePrimary = Color(0xFFEA580C)
val OrangeDark = Color(0xFFC2410C)

val PurplePrimary = Color(0xFF7C3AED)
val PurpleDark = Color(0xFF5B21B6)

enum class AppAccentMode {
    ROSE, BLUE, GREEN, ORANGE, PURPLE
}

fun getAccentColors(mode: AppAccentMode): Pair<Color, Color> {
    return when (mode) {
        AppAccentMode.ROSE -> Pair(RosePrimary, RoseDark)
        AppAccentMode.BLUE -> Pair(BluePrimary, BlueDark)
        AppAccentMode.GREEN -> Pair(GreenPrimary, GreenDark)
        AppAccentMode.ORANGE -> Pair(OrangePrimary, OrangeDark)
        AppAccentMode.PURPLE -> Pair(PurplePrimary, PurpleDark)
    }
}
