package com.example.myapplication.ui.features.players_names

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.PlayerNames

@Composable
fun PlayerNamesRootScreen(
    onNavAction: (String) -> Unit,
    playerNamesViewModel: PlayerNamesViewModel = hiltViewModel<PlayerNamesViewModel>()
) {
    val onClick: (PlayerNames) -> Unit = {
        playerNamesViewModel.onSaveClick(it)
    }

    EnterPlayerNamesScreen(
        onNavAction = onNavAction,
        playerNamesStateFlow = playerNamesViewModel.uiStateFlow,
        onClick = onClick
    )


}
