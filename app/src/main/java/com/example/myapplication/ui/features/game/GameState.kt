package com.example.myapplication.ui.features.game

import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

data class GameState(
    val cellsState: Map<CellPosition, PlayerSign?> = emptyMap(),
    val turn: PlayerSign = PlayerSign.X,
    val failure: Failure? = null
)
