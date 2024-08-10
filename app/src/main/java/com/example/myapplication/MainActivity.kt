package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.ui.DrawCells
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val vm: GameViewModel = GameViewModel()
    private lateinit var state: State<GameState>
    private val onClick: (CellPosition) -> Unit = { position ->
        vm.onCellClick(position = position)
    }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            state = vm.uiStateFlow.collectAsStateWithLifecycle()

            MyApplicationTheme {
                navController = rememberNavController()

//                NavHost(navController = navController, startDestination = "GameRoute") {
                NavHost(navController = navController, startDestination = "EnterYourNames") {
                    composable("EnterYourNames") { EnterPlayerNamesScreen() }
                    composable("GameRoute") { GameScreen() }
                }

                state.value.failure?.let {
                    handleFailure(failure = it)
                }
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

    @Composable
    fun GameScreen() {
        DrawCells(
            cellsState = state.value.cellsState,
            onClick = onClick
        )
    }

    @Composable
    fun EnterPlayerNamesScreen() {
        Column(modifier = Modifier.fillMaxSize()
            .padding(20.dp)) {

            Text(text = stringResource(id = R.string.enter_your_names))

            var player1Text by rememberSaveable { mutableStateOf("Player 1") }
            var player2Text by rememberSaveable { mutableStateOf("Player 2") }

            TextField(
                value = player1Text,
                onValueChange = {
                    player1Text = it
                },
                label = { Text("Player 1") }
            )

            TextField(
                value = player2Text,
                onValueChange = {
                    player2Text = it
                },
                label = { Text("Player 1") }
            )

            Button(onClick = {
                println("@@@BUTTON")
                navController.navigate(route = "GameRoute")
            }) {
                Text("Go to Profile")
            }
        }
    }
}
