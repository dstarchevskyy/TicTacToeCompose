package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition
import com.example.myapplication.ui.features.game.GameScreen
import com.example.myapplication.ui.features.players_names.EnterPlayerNamesScreen
import com.example.myapplication.ui.features.players_names.PlayerNamesViewModel
import com.example.myapplication.ui.navigation.NavRoutes
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var vm: GameViewModel
    private lateinit var playerNamesViewModel: GameViewModel
    private lateinit var state: State<GameState>

    private val onClick: (CellPosition) -> Unit = { position ->
        vm.onCellClick(position = position)
    }

    private val onNavAction: (String) -> Unit = { route ->
        navController.navigate(route = route)
    }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            vm = hiltViewModel()

            state = vm.uiStateFlow.collectAsStateWithLifecycle()

            MyApplicationTheme {
                navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.ROUTE_PLAYER_NAMES
                ) {
                    composable(NavRoutes.ROUTE_PLAYER_NAMES) {
                        val playerNamesViewModel = hiltViewModel<PlayerNamesViewModel>()
                        EnterPlayerNamesScreen(
                            onNavAction = onNavAction,
                            playerNamesStateFlow = playerNamesViewModel.uiStateFlow
                        )
                    }
                    composable(NavRoutes.ROUTE_GAME) {
                        GameScreen(
                            cellsState = state.value.cellsState,
                            onClick = onClick
                        )
                    }
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
}
