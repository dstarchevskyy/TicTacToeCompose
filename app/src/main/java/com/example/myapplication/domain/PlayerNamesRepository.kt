package com.example.myapplication.domain

interface PlayerNamesRepository {
    fun savePlayerNames(
        playerNames: PlayerNames
    )

    fun readPlayerNames(): PlayerNames
}