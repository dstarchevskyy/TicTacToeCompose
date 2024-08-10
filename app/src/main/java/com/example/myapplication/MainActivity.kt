package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.ui.DrawCells
import com.example.myapplication.ui.theme.MyApplicationTheme

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

    @Composable
    private fun drawWinLine() {
        val configuration: Configuration = LocalConfiguration.current

        val screenWidth: Float = configuration.screenWidthDp.toFloat()
        val screenHeight: Float = configuration.screenHeightDp.toFloat()


        Canvas(modifier = Modifier.fillMaxSize()) {
            drawLine(
                color = Color.Cyan,
                start = Offset.Zero,
                end = Offset(screenWidth,screenHeight),
                strokeWidth = 15f,
            )
        }

    }
}
