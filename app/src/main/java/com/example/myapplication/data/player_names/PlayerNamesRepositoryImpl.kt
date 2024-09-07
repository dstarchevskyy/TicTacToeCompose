package com.example.myapplication.data.player_names

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.domain.PlayerNames
import com.example.myapplication.domain.PlayerNamesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerNamesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PlayerNamesRepository {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun savePlayerNames(
        playerNames: PlayerNames
    ) = with(editor) {
        putString(PLAYER_ONE_NAME_KEY, playerNames.playerOneName)
        putString(PLAYER_TWO_NAME_KEY, playerNames.playerTwoName)

        apply()
    }

    override fun readPlayerNames(): PlayerNames = with(sharedPreferences) {
        val playerOneName: String? = getString(PLAYER_ONE_NAME_KEY, null)
        val playerTwoName: String? = getString(PLAYER_TWO_NAME_KEY, null)

        return PlayerNames(
            playerOneName = playerOneName,
            playerTwoName = playerTwoName
        )
    }
}

const val PREFERENCES_NAME = "GAME_PREFS_NAME"
const val PLAYER_ONE_NAME_KEY = "PLAYER_ONE_NAME_KEY"
const val PLAYER_TWO_NAME_KEY = "PLAYER_TWO_NAME_KEY"