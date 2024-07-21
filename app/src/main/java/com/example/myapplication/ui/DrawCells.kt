package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.MAX_CELL_NUMBER
import com.example.myapplication.domain.PlayerSign

@Composable
fun DrawCells(
    cellsState: Map<CellPosition, PlayerSign?> = emptyMap(),
    onClick: (CellPosition) -> Unit
) {
    MyRow()
}

@Composable
private fun MyColumn(
    x: Int
) {
    Column() {
        for (y in 0..MAX_CELL_NUMBER) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f, true)
                .border(3.dp, color = Color.Blue)
                .padding(20.dp)) {
                Text(text = "Y1")
            }
        }
    }
}

@Composable
private fun MyRow() {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red),
    ) {
        for (x in 0..MAX_CELL_NUMBER) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f, true)
                .border(3.dp, color = Color.Blue)
                .padding(20.dp)) {
                MyColumn(x = x)
            }
        }
    }
}