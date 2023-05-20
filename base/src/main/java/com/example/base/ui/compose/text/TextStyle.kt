package com.example.base.ui.compose.text

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.base.ui.theme.AppColors
import com.example.base.ui.theme.SharedFontSize


@Composable
fun titleTextStyle(color: Color = AppColors.Black) =
    TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = SharedFontSize.Large,
        fontFamily = LocalTextStyle.current.fontFamily,
        color = color,
        letterSpacing = (-0.5).sp
    )

@Composable
fun subtitleTextStyle(color: Color = AppColors.White) =
    TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = SharedFontSize.Small,
        fontFamily = LocalTextStyle.current.fontFamily,
        color = color,
        letterSpacing = (-0.5).sp
    )

@Composable
fun boldSubtitleTextStyle() =
    TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = SharedFontSize.Medium,
        fontFamily = LocalTextStyle.current.fontFamily,
        color = AppColors.White,
        letterSpacing = (-0.5).sp
    )

@Composable
fun blackSubtitleTextStyle() =
    TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = SharedFontSize.Small,
        fontFamily = LocalTextStyle.current.fontFamily,
        color = AppColors.Black,
        letterSpacing = (-0.5).sp
    )

@Composable
fun buttonTextStyle() =
    TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = SharedFontSize.Small2,
        fontFamily = LocalTextStyle.current.fontFamily,
        color = AppColors.White
    )


@Composable
fun toolbarTextStyle(color: Color = AppColors.White) = TextStyle(
    color = color,
    fontWeight = FontWeight.Medium,
    fontSize = SharedFontSize.Small2,
    fontFamily = LocalTextStyle.current.fontFamily,
    letterSpacing = (-0.5).sp
)


/*          */
@Composable
fun smallStyle400() = TextStyle(
    fontWeight = FontWeight.W400,
    fontSize = SharedFontSize.Small,
    fontFamily = LocalTextStyle.current.fontFamily
)

@Composable
fun smallStyle600() = TextStyle(
    fontWeight = FontWeight.W600,
    fontSize = SharedFontSize.Small,
    fontFamily = LocalTextStyle.current.fontFamily
)

@Composable
fun normalStyle() = TextStyle(
    fontSize = SharedFontSize.Small,
    fontWeight = FontWeight.W400,
    fontFamily = LocalTextStyle.current.fontFamily
)

@Composable
fun boldStyle(
    color: Color = AppColors.Text,
    fontSize: TextUnit = SharedFontSize.Small,
) =
    TextStyle(
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = color,
        fontFamily = LocalTextStyle.current.fontFamily
    )

@Composable
fun bodyStyle(color: Color = AppColors.Text) = TextStyle(
    fontSize = SharedFontSize.Piddling2,
    fontWeight = FontWeight.Normal,
    fontFamily = LocalTextStyle.current.fontFamily,
    color = color,
    letterSpacing = (-0.5).sp
)

@Composable
fun captionStyle() = TextStyle(
    fontSize = SharedFontSize.Piddling,
    fontWeight = FontWeight.Normal,
    fontFamily = LocalTextStyle.current.fontFamily,
    color = AppColors.Text,
    letterSpacing = (-0.5).sp
)


@Composable
fun hintTextStyle(fontSize: TextUnit = SharedFontSize.Small) = TextStyle(
    color = AppColors.TextHint,
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    fontFamily = LocalTextStyle.current.fontFamily,
    letterSpacing = (0.44).sp
)


@Composable
fun error500TextStyle() = TextStyle(
    color = AppColors.Error,
    textAlign = TextAlign.Center,
    fontWeight = FontWeight.Normal,
    fontSize = SharedFontSize.Small,
    fontFamily = LocalTextStyle.current.fontFamily,
    lineHeight = 20.sp,
    letterSpacing = (0.15).sp
)

@Composable
fun black12TextStyle() = TextStyle(
    color = AppColors.Text,
    fontWeight = FontWeight.Normal,
    fontSize = SharedFontSize.Piddling2,
    fontFamily = LocalTextStyle.current.fontFamily,
    letterSpacing = (-0.5).sp
)

