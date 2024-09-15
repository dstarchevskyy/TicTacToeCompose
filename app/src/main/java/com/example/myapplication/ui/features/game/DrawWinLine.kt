package com.example.myapplication.ui.features.game

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

@Composable
fun DrawWinLine(
    cellsState: Map<CellPosition, PlayerSign?>
) {
    val configuration: Configuration = LocalConfiguration.current

    val density: Int =  configuration.densityDpi

    val screenWidth: Float = (configuration.screenWidthDp * density).toFloat()
    val screenHeight: Float = (configuration.screenHeightDp * density).toFloat()

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            color = Color.Cyan,
            start = Offset.Zero,
            end = Offset(screenWidth,screenHeight),
            strokeWidth = 15f,
        )
    }

    println("@@@isWin: ${getWinData(cellsState)}")
}

private fun getWinData(cellsState: Map<CellPosition, PlayerSign?>): WinData? {
    val verticalWinData: WinData? = getVerticalWinData(cellsState)
    val horizontalWinData: WinData? = getHorizontalWinData(cellsState)

    return verticalWinData ?: horizontalWinData
}

private fun getVerticalWinData(cellsState: Map<CellPosition, PlayerSign?>): WinData? {
    var signCrossCount: Int
    var signZeroCount: Int
    var winnerSign: PlayerSign
    var winData: WinData? = null

    for (columnNumber: Int in 0..2) {
        signCrossCount = 0
        signZeroCount = 0

        for (rowNumber in 0..2) {
            val cellPosition = CellPosition(x = columnNumber, y = rowNumber)
            val sign: PlayerSign? = cellsState[cellPosition]

            when (sign) {
                PlayerSign.X -> {
                    signCrossCount++
                }
                PlayerSign.O -> {
                    signZeroCount++
                }
                null -> {
                    continue
                }
            }
        }

        winnerSign = when {
            signCrossCount == 3 -> PlayerSign.X
            signZeroCount == 3 -> PlayerSign.O
            else -> continue
        }

        winData = WinData(
            winnerSign = winnerSign,
            direction = WinData.WinDirection.VERTICAL,
            position = columnNumber
        )
    }

    return winData
}

private fun getHorizontalWinData(cellsState: Map<CellPosition, PlayerSign?>): WinData? {
    var signCrossCount: Int
    var signZeroCount: Int
    var winnerSign: PlayerSign
    var winData: WinData? = null

    for (rowNumber: Int in 0..2) {
        signCrossCount = 0
        signZeroCount = 0

        for (columnNumber in 0..2) {
            val cellPosition = CellPosition(x = columnNumber, y = rowNumber)
            val sign: PlayerSign? = cellsState[cellPosition]

            when (sign) {
                PlayerSign.X -> {
                    signCrossCount++
                }
                PlayerSign.O -> {
                    signZeroCount++
                }
                null -> {
                    continue
                }
            }
        }

        winnerSign = when {
            signCrossCount == 3 -> PlayerSign.X
            signZeroCount == 3 -> PlayerSign.O
            else -> continue
        }

        winData = WinData(
            winnerSign = winnerSign,
            direction = WinData.WinDirection.HORIZONATAL,
            position = rowNumber
        )
    }

    return winData
}