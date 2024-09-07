package com.example.myapplication.domain

interface PlayerNamesRepository {
    fun savePlayerNames(
        playerOneName: String,
        playerTwoName: String
    )
}