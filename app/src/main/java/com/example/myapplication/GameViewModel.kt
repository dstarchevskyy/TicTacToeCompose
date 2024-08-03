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
        val currentGameStat: GameState = uiStateFlow.value
        
        val newCellState: MutableMap<CellPosition, PlayerSign?> = currentGameStat.cellsState.toMutableMap()
        newCellState[position] = currentGameStat.turn

        val newGameState: GameState = currentGameStat.copy(
            cellsState = newCellState,
            turn = getNewTurn(currentGameStat.turn)
        )
        _uiStateFlow.value = newGameState
    }

    private fun getNewTurn(currentTurn: PlayerSign): PlayerSign {
        return if (currentTurn == PlayerSign.X) {
            PlayerSign.O
        } else {
            PlayerSign.X
        }
    }
}