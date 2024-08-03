package com.example.myapplication

import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel {
    private val _uiStateFlow: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val uiStateFlow: StateFlow<GameState> = _uiStateFlow

    fun onCellClick(position: CellPosition) {
        println("@@@onCellClick: $position")
        val newCellState: MutableMap<CellPosition, PlayerSign?> = _uiStateFlow.value.cellsState.toMutableMap()
        newCellState[position] = PlayerSign.X

        _uiStateFlow.value = GameState(
            cellsState = newCellState
        )
    }
}