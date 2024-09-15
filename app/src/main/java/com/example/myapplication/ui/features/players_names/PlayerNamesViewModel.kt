package com.example.myapplication.ui.features.players_names

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.PlayerNames
import com.example.myapplication.domain.PlayerNamesRepository
import com.example.myapplication.domain.interactors.ReadPlayerNames
import com.example.myapplication.domain.interactors.SavePlayerNames
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerNamesViewModel @Inject constructor(
    private val savePlayerNames: SavePlayerNames,
    private val readPlayerNames: ReadPlayerNames
): ViewModel() {
    private val _uiStateFlow: MutableStateFlow<PlayerNamesState> = MutableStateFlow(PlayerNamesState())
    val uiStateFlow: StateFlow<PlayerNamesState> = _uiStateFlow

    init {
        viewModelScope.launch {
            val playerNames: PlayerNames = readPlayerNames()
            _uiStateFlow.emit(PlayerNamesState(playerNames = playerNames))
        }
    }

    fun onSaveClick(playerNames: PlayerNames) {
        savePlayerNames(playerNames)
    }
}