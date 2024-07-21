package com.example.myapplication

import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

data class GameState(
    val cellsState: Map<CellPosition, PlayerSign?> = emptyMap(),
    val turn: PlayerSign = PlayerSign.X
)
