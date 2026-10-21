package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
  darkColorScheme(
    primary = EmeraldGreenPrimaryDark,
    onPrimary = EmeraldGreenOnPrimaryDark,
    primaryContainer = EmeraldGreenPrimaryContainerDark,
    onPrimaryContainer = EmeraldGreenOnPrimaryContainerDark,
    secondary = MintGreenSecondaryDark,
    onSecondary = MintGreenOnSecondaryDark,
    secondaryContainer = MintGreenSecondaryContainerDark,
    onSecondaryContainer = MintGreenOnSecondaryContainerDark,
    background = CleanBackgroundDark,
    onBackground = CleanOnBackgroundDark,
    surface = CleanSurfaceDark,
    onSurface = CleanOnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
  )

private val LightColorScheme =
  lightColorScheme(
    primary = EmeraldGreenPrimary,
    onPrimary = EmeraldGreenOnPrimary,
    primaryContainer = EmeraldGreenPrimaryContainer,
    onPrimaryContainer = EmeraldGreenOnPrimaryContainer,
    secondary = MintGreenSecondary,
    onSecondary = MintGreenOnSecondary,
    secondaryContainer = MintGreenSecondaryContainer,
    onSecondaryContainer = MintGreenOnSecondaryContainer,
    tertiary = SoftTealTertiary,
    onTertiary = SoftTealOnTertiary,
    tertiaryContainer = SoftTealTertiaryContainer,
    onTertiaryContainer = SoftTealOnTertiaryContainer,
    background = CleanBackgroundLight,
    onBackground = CleanOnBackgroundLight,
    surface = CleanSurfaceLight,
    onSurface = CleanOnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
  )

@Composable
fun Desk2FitTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
