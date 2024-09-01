package com.example.myapplication.ui.features.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.MAX_CELL_NUMBER
import com.example.myapplication.domain.PlayerSign
import com.example.myapplication.ui.Cell

@Composable
fun DrawCells(
    cellsState: Map<CellPosition, PlayerSign?> = emptyMap(),
    onClick: (CellPosition) -> Unit
) {
    println("@@@DrawCells: $cellsState")

    MyRow(
        onClick = onClick,
        cellsState = cellsState,

    )
}
@Composable
private fun MyRow(
    cellsState: Map<CellPosition, PlayerSign?>,
    onClick: (CellPosition) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Red)
        ,
    ) {
        for (x in 0..MAX_CELL_NUMBER) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f, true)
                .border(3.dp, color = Color.Blue)) {
                MyColumn(
                    cellsState = cellsState,
                    x = x,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
private fun MyColumn(
    cellsState: Map<CellPosition, PlayerSign?>,
    x: Int,
    onClick: (CellPosition) -> Unit
) {
    Column() {
        for (y in 0..MAX_CELL_NUMBER) {
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f, true)
                .border(3.dp, color = Color.Blue)) {

                val cellPosition = CellPosition(
                    x = x,
                    y = y,
                )

                Cell(
                    position = cellPosition,
                    sign = cellsState[cellPosition],
                    onClick = {
                        onClick(CellPosition(x = x, y = y))
                    }
                )
            }
        }
    }
}
