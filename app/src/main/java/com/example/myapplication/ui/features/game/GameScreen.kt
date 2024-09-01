package com.example.myapplication.ui.features.game

import androidx.compose.runtime.Composable
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

@Composable
fun GameScreen(
    cellsState: Map<CellPosition, PlayerSign?> = emptyMap(),
    onClick: (CellPosition) -> Unit
) {
    DrawCells(
        cellsState = cellsState,
        onClick = onClick
    )
}
