package com.farman.afgarments.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

class AFGarmentsColors(
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val border: Color,
    val accentPrimary: Color,
    val accentDark: Color,
    val positive: Color,
    val negative: Color,
    val isLight: Boolean
)

val LocalAppColors = staticCompositionLocalOf<AFGarmentsColors> {
    error("No AppColors provided")
}

@Composable
fun AFGarmentsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    accentMode: AppAccentMode = AppAccentMode.ROSE,
    content: @Composable () -> Unit
) {
    val (accentPrimary, accentDark) = getAccentColors(accentMode)

    val colors = if (darkTheme) {
        AFGarmentsColors(
            background = DarkBackground,
            surface = DarkSurface,
            textPrimary = DarkTextPrimary,
            textSecondary = DarkTextSecondary,
            border = DarkBorder,
            accentPrimary = accentPrimary,
            accentDark = accentDark,
            positive = PosGreen,
            negative = NegRed,
            isLight = false
        )
    } else {
        AFGarmentsColors(
            background = LightBackground,
            surface = LightSurface,
            textPrimary = LightTextPrimary,
            textSecondary = LightTextSecondary,
            border = LightBorder,
            accentPrimary = accentPrimary,
            accentDark = accentDark,
            positive = PosGreen,
            negative = NegRed,
            isLight = true
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    // Still providing Material3 theme for standard components, but custom colors for custom components
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = accentPrimary,
            secondary = accentDark,
            background = colors.background,
            surface = colors.surface,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = colors.textPrimary,
            onSurface = colors.textPrimary,
            error = NegRed
        )
    } else {
        lightColorScheme(
            primary = accentPrimary,
            secondary = accentDark,
            background = colors.background,
            surface = colors.surface,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onBackground = colors.textPrimary,
            onSurface = colors.textPrimary,
            error = NegRed
        )
    }

    CompositionLocalProvider(LocalAppColors provides colors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

object AFTheme {
    val colors: AFGarmentsColors
        @Composable
        get() = LocalAppColors.current
}
