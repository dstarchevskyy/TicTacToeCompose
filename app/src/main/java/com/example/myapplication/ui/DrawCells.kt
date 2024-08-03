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
    MyRow(onClick = onClick)
}
@Composable
private fun MyRow(onClick: (CellPosition) -> Unit) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red),
    ) {
        for (x in 0..MAX_CELL_NUMBER) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f, true)
                .border(3.dp, color = Color.Blue)) {
                MyColumn(
                    x = x,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun MyColumn(
    x: Int,
    onClick: (CellPosition) -> Unit
) {
    Column() {
        for (y in 0..MAX_CELL_NUMBER) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f, true)
                .border(3.dp, color = Color.Blue)) {
                Cell(
                    position = CellPosition(
                        x = x,
                        y = y,
                    ),
                    onClick = {
                        onClick(CellPosition(x = x, y = y))
                    }
                )
            }
        }
    }
}
