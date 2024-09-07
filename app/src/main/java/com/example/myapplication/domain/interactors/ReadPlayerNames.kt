package com.example.myapplication.domain.interactors

import com.example.myapplication.domain.PlayerNames
import com.example.myapplication.domain.PlayerNamesRepository
import javax.inject.Inject

class ReadPlayerNames @Inject constructor(
    private val playerNamesRepository: PlayerNamesRepository
) {
    operator fun invoke(): PlayerNames {
        return playerNamesRepository.readPlayerNames()
    }
}