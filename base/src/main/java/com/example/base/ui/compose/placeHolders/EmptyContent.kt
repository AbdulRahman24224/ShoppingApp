package com.example.base.ui.compose.placeHolders

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.base.ui.theme.AppColors.Black

@Composable
fun EmptyContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(248.dp),
            painter = painterResource(id = com.example.shoppingapp.R.drawable.ic_warning),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = modifier.fillMaxWidth(),
            text = "No data",

            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Black,
            textAlign = TextAlign.Center,
        )
    }
}
