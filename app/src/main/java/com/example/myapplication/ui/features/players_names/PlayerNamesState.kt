package com.example.myapplication.ui.features.players_names

import com.example.myapplication.domain.PlayerNames

data class PlayerNamesState(
    val playerNames: PlayerNames = PlayerNames(
        playerOneName = null,
        playerTwoName = null
    )
)