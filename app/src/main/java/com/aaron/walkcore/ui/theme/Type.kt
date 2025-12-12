package com.aaron.walkcore.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aaron.walkcore.R

// Set of Material typography styles to start with

val MontserratAlternatesFontFamily = FontFamily(
    Font(R.font.montserrat_alternates_thin, FontWeight.Thin),
    Font(R.font.montserrat_alternates_extralight, FontWeight.ExtraLight),
    Font(R.font.montserrat_alternates_light, FontWeight.Light),
    Font(R.font.montserrat_alternates_regular, FontWeight.Normal),
    Font(R.font.montserrat_alternates_medium, FontWeight.Medium),
    Font(R.font.montserrat_alternates_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_alternates_bold, FontWeight.Bold),
    Font(R.font.montserrat_alternates_extrabold, FontWeight.ExtraBold),
    Font(R.font.montserrat_alternates_black, FontWeight.Black)
)

val GeoramaFontFamily = FontFamily(
    Font(R.font.georama_variable)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = MontserratAlternatesFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = MontserratAlternatesFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = MontserratAlternatesFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = GeoramaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = GeoramaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    bodySmall = TextStyle(
        fontFamily = GeoramaFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)