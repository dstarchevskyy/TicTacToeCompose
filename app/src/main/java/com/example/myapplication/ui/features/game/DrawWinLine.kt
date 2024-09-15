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

    println("@@@isWin: ${isWin(cellsState)}")
}

private fun isWin(cellsState: Map<CellPosition, PlayerSign?>): Boolean {
    var signCrossCount = 0
    var signZeroCount = 0

    for (i in 0..2) {
        val cellPosition = CellPosition(0, i)
        val sign: PlayerSign? = cellsState[cellPosition]

        when (sign) {
            PlayerSign.X -> signCrossCount++
            PlayerSign.O -> signZeroCount++
            null -> continue
        }
    }

    return (signCrossCount == 3 || signZeroCount == 3)
}