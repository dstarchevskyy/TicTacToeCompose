package com.example.myapplication.data.player_names

import android.content.Context
import com.example.myapplication.domain.PlayerNamesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PlayerNamesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PlayerNamesRepository {
    override fun savePlayerNames(
        playerOneName: String,
        playerTwoName: String
    ) {
        println("@@@savePlayerNames: $context")
    }
}