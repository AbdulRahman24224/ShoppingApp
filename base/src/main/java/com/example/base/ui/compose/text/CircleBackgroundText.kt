package com.example.base.ui.compose.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base.ui.theme.AppColors

@Composable
fun CircleBackgroundText(
    text: String,
    circleBackgroundColor: Color = AppColors.Green1,
    textColor: Color = AppColors.White,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(CircleShape)
            .background(circleBackgroundColor)

    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .align(Alignment.Center),
            text = text,
            style = boldStyle().copy(color = textColor)
        )
    }
}

@Preview
@Composable
fun PreviewCircleBackgroundText() {
    CircleBackgroundText("35")
}