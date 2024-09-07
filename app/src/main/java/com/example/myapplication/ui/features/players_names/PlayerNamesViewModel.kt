package com.example.myapplication.ui.features.players_names

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.PlayerNamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerNamesViewModel @Inject constructor(
    playerNamesRepository: PlayerNamesRepository
): ViewModel() {
    private val _uiStateFlow: MutableStateFlow<PlayerNamesState> = MutableStateFlow(PlayerNamesState())
    val uiStateFlow: StateFlow<PlayerNamesState> = _uiStateFlow

    init {
        println("@@@playerNamesRepository: $playerNamesRepository")
    }
}