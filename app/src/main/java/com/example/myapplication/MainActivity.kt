package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.features.game.GameRootScreen
import com.example.myapplication.ui.features.players_names.PlayerNamesRootScreen
import com.example.myapplication.ui.navigation.NavRoutes
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val onNavAction: (String) -> Unit = { route ->
        navController.navigate(route = route)
    }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.ROUTE_PLAYER_NAMES
                ) {
                    composable(NavRoutes.ROUTE_PLAYER_NAMES) {
                        PlayerNamesRootScreen(onNavAction = onNavAction)
                    }
                    composable(NavRoutes.ROUTE_GAME) {
                        GameRootScreen()
                    }
                }
            }
        }
    }
}
