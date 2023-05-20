package com.example.base.ui.compose.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.base.ui.theme.AppColors


@Composable
fun IconButton(
    modifier: Modifier,
    icon: ImageVector = Icons.Default.Add,
    backgroundColor: Color = AppColors.Yellow4,
    onButtonClick: () -> Unit,

) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(14.dp),
        contentPadding = PaddingValues(10.dp),
        onClick = onButtonClick
    )
    {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = icon,
            tint = Color.White,
            contentDescription = null
        )
    }
}