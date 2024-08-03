package com.example.myapplication

import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel {
    private val _uiStateFlow: MutableStateFlow<GameState> = MutableStateFlow(GameState())
    val uiStateFlow: StateFlow<GameState> = _uiStateFlow

    fun onCellClick(position: CellPosition) {
        val currentGameStat: GameState = uiStateFlow.value

        val playerSign: PlayerSign? = currentGameStat.cellsState[position]
        if (playerSign != null) {
            val newGameState: GameState = currentGameStat.copy(
                failure = Failure("This cell already occupied")
            )
            _uiStateFlow.value = newGameState
            return // Show msg Cell already occupied
        }

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

    fun resetFailure() {
        _uiStateFlow.value = uiStateFlow.value.copy(
            failure = null
        )
    }
}