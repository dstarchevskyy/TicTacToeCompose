package com.example.myapplication.domain.interactors

import com.example.myapplication.domain.PlayerNames
import com.example.myapplication.domain.PlayerNamesRepository
import javax.inject.Inject

class SavePlayerNames @Inject constructor(
    private val playerNamesRepository: PlayerNamesRepository
) {
    operator fun invoke(playerNames: PlayerNames) {
        playerNamesRepository.savePlayerNames(playerNames)
    }
}