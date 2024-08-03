package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign
import com.example.myapplication.ui.Cell
import com.example.myapplication.ui.DrawCells
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val vm: GameViewModel = GameViewModel()

    private val onClick: (CellPosition) -> Unit = { position ->
        println("@@@test!!! x:${position.x}, y:${position.y}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val gameState: GameState = vm.uiState.value

        setContent {
            MyApplicationTheme {
                DrawCells(
                    cellsState = gameState.cellsState,
                    onClick = onClick
                )
            }
        }
    }
}
