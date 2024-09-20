package com.example.myapplication.ui.features.game

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign

@Composable
fun DrawWinLine(
    cellsState: Map<CellPosition, PlayerSign?>
) {
    println("@@@isWin: ${getWinData(cellsState)}")

    val winData = getWinData(cellsState)

    var startOffset: Offset = Offset.Zero
    var endOffset: Offset = Offset.Zero

    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val cellWidth = canvasWidth / 3
        val cellHalfWidth = cellWidth / 2

        when {
            winData?.direction == WinData.WinDirection.MAIN_DIAGONAL -> {
                startOffset = Offset.Zero
                endOffset = Offset(canvasWidth, canvasHeight)
            }
            winData?.direction == WinData.WinDirection.SECONDARY_DIAGONAL -> {
                startOffset = Offset(canvasWidth, 0f)
                endOffset = Offset(0f, canvasHeight)
            }
            winData?.direction == WinData.WinDirection.VERTICAL -> {
                val x: Float = (cellWidth * (winData.position ?: 0)) + cellHalfWidth

                startOffset = Offset(
                    x = x,
                    y = 0f
                )
                endOffset = Offset(
                    x = x,
                    y = canvasHeight
                )
            }
            else -> { }
        }


        drawLine(
            color = Color.Cyan,
            start = startOffset,
            end = endOffset,
            strokeWidth = 15f,
        )
    }
}

private fun getWinData(cellsState: Map<CellPosition, PlayerSign?>): WinData? {
    val verticalWinData: WinData? = getVerticalWinData(cellsState)
    val horizontalWinData: WinData? = getHorizontalWinData(cellsState)
    val mainDiagonalWinData: WinData? = getMainDiagonalWinData(cellsState)
    val secondaryDiagonalWinData: WinData? = getSecondaryDiagonalWinData(cellsState)

    return verticalWinData ?: horizontalWinData ?: mainDiagonalWinData ?: secondaryDiagonalWinData
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

private fun getMainDiagonalWinData(cellsState: Map<CellPosition, PlayerSign?>): WinData? {
    var signCrossCount = 0
    var signZeroCount = 0
    val winData: WinData?

    for (diagonalPosition in 0..2) {
            val cellPosition = CellPosition(x = diagonalPosition, y = diagonalPosition)
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

    val winnerSign: PlayerSign = when {
            signCrossCount == 3 -> PlayerSign.X
            signZeroCount == 3 -> PlayerSign.O
            else -> return null
        }

        winData = WinData(
            winnerSign = winnerSign,
            direction = WinData.WinDirection.MAIN_DIAGONAL,
            position = null
        )

    return winData
}

private fun getSecondaryDiagonalWinData(cellsState: Map<CellPosition, PlayerSign?>): WinData? {
    var signCrossCount = 0
    var signZeroCount = 0
    val winData: WinData?

    for (diagonalPosition in 0..2) {
        val cellPosition = CellPosition(x = diagonalPosition, y = (2 - diagonalPosition))
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

    val winnerSign: PlayerSign = when {
        signCrossCount == 3 -> PlayerSign.X
        signZeroCount == 3 -> PlayerSign.O
        else -> return null
    }

    winData = WinData(
        winnerSign = winnerSign,
        direction = WinData.WinDirection.SECONDARY_DIAGONAL,
        position = null
    )

    return winData
}
