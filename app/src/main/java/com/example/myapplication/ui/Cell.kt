package com.example.myapplication.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.CellPosition

@Composable
fun Cell(
    position: CellPosition,
    modifier: Modifier,
    onClick: (CellPosition) -> Unit) {
    Box(modifier = Modifier
        .border(
            border = BorderStroke(
                width = 5.dp,
                color = Color.Red
            )
        )
        .clickable { onClick(position) }
    ) {
        Text(
            text = "X",
            modifier = modifier.padding(
                20.dp
            )
        )
    }
}