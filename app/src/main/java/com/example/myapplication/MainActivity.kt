package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign
import com.example.myapplication.ui.DrawCells
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val vm: GameViewModel = GameViewModel()

    private val onClick: (CellPosition) -> Unit = { position ->
        println("@@@test!!! x:${position.x}, y:${position.y}")
        vm.onCellClick(position = position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cellsState: Map<CellPosition, PlayerSign?> = vm.uiStateFlow.value.cellsState
        
        setContent {
            MyApplicationTheme {

                val state: State<GameState> = vm.uiStateFlow.collectAsStateWithLifecycle()


                DrawCells(
//                    cellsState = cellsState,
                    cellsState = state.value.cellsState,
                    onClick = onClick
                )
            }
        }

        lifecycleScope.launch {
            vm.uiStateFlow.collect {
                println("@@@collect: $it")
            }
        }
    }
}
