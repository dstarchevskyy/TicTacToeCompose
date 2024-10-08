package com.example.myapplication.ui.features.players_names

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.domain.PlayerNames
import com.example.myapplication.ui.navigation.NavRoutes
import kotlinx.coroutines.flow.StateFlow

@Composable
fun EnterPlayerNamesScreen(
    onNavAction: (String) -> Unit,
    playerNamesStateFlow: StateFlow<PlayerNamesState>,
    onClick: (PlayerNames) -> Unit
) {
    val state: State<PlayerNamesState> = playerNamesStateFlow.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .imePadding()
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = stringResource(id = R.string.enter_your_names))

        var player1Text by rememberSaveable { mutableStateOf( state.value.playerNames.playerOneName ?: "Player 1") }
        var player2Text by rememberSaveable { mutableStateOf(state.value.playerNames.playerTwoName ?: "Player 2") }

        TextField(
            value = player1Text,
            modifier = Modifier.padding(top = 20.dp),
            onValueChange = { player1Text = it },
            label = { Text(text = stringResource(id = R.string.player_1)) }
        )

        TextField(
            value = player2Text,
            modifier = Modifier.padding(top = 20.dp),
            onValueChange = { player2Text = it },
            label = { Text(text = stringResource(id = R.string.player_2)) }
        )

        Button(
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                onClick(
                    PlayerNames(
                        playerOneName = player1Text,
                        playerTwoName = player2Text
                    )
                )

                onNavAction(NavRoutes.ROUTE_GAME)
            }) {
            Text(text = stringResource(id = R.string.start_game))
        }
    }
}