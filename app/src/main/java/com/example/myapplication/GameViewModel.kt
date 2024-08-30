package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(): ViewModel() {
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