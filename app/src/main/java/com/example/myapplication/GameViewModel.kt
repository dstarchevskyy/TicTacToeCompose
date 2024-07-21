package com.example.myapplication

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.domain.CellPosition

class GameViewModel {
    private val _uiState: MutableState<GameState> = mutableStateOf(GameState())
    val uiState: State<GameState> = _uiState

    fun onCellClick(position: CellPosition) {

    }
}