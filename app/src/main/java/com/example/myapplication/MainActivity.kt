package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.domain.PlayerSign
import com.example.myapplication.ui.DrawCells
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val vm: GameViewModel = GameViewModel()

    private val onClick: (CellPosition) -> Unit = { position ->
        vm.onCellClick(position = position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {

                val state: State<GameState> = vm.uiStateFlow.collectAsStateWithLifecycle()

                state.value.failure?.let {
                    handleFailure(failure = it)
                }

                DrawCells(
                    cellsState = state.value.cellsState,
                    onClick = onClick
                )
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        vm.resetFailure()
        Toast.makeText(this, failure.errorMessage, Toast.LENGTH_SHORT).show()
    }
}
