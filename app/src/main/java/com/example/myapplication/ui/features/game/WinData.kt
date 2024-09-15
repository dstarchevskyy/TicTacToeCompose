package com.example.myapplication.ui.features.game

import com.example.myapplication.domain.PlayerSign

data class WinData(
    val winnerSign: PlayerSign,
    val direction: WinDirection,
    val position: Int? = null
) {
    enum class WinDirection {
        HORIZONATAL,
        VERTICAL,
        MAIN_DIAGONAL,
        SECONDARY_DIAGONAL
    }
}