package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

@Composable
fun Cell(
    position: CellPosition,
    sign: PlayerSign? = null,
    onClick: (CellPosition) -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .border(
            border = BorderStroke(
                width = 5.dp,
                color = Color.Black
            )
        )
        .clickable { onClick(position) }
    ) {
        println("@@@sign?.toString(): ${sign?.toString()}")
        val textColor: Color = if (sign == PlayerSign.X) {
            Color.Blue
        } else {
            Color.Red
        }

        Text(
            text = sign?.toString() ?: "",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
                .background(color = Color.Yellow)
                .align(alignment = Alignment.Center)
                .wrapContentHeight(align = Alignment.CenterVertically),
            fontSize = 48.sp,
            color = textColor
        )
    }
}