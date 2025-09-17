package ru.mareanexx.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    onSecondaryContainer = LightOnSecondaryContainer,
    tertiary = LightTertiary,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceContainerHighest = LightSurfaceContainerHighest,
    onSurfaceVariant = LightOnSurfaceVariant
)

// TODO: придумать цвета для темной темы
private val DarkColorScheme = LightColorScheme

@Composable
fun LingvooTranslatorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        shapes = Shapes,
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}