package com.example.myapplication.ui.features.game

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.GameViewModel
import com.example.myapplication.data.Failure
import com.example.myapplication.domain.CellPosition

private lateinit var state: State<GameState>

@Composable
fun GameRootScreen(
    vm: GameViewModel = hiltViewModel<GameViewModel>()
) {
    state = vm.uiStateFlow.collectAsStateWithLifecycle()

    val resetFailure: () -> Unit = {
        vm.resetFailure()
    }

    val onClick: (CellPosition) -> Unit = { position ->
        vm.onCellClick(position = position)
    }

    GameScreen(
        cellsState = state.value.cellsState,
        onClick = onClick
    )

    state.value.failure?.let {
        HandleFailure(
            resetFailure = resetFailure,
            failure = it
        )
    }
}

@Composable
private fun HandleFailure(
    resetFailure: () -> Unit,
    failure: Failure
) {
    resetFailure()

    val context: Context = LocalContext.current

    Toast.makeText(context, failure.errorMessage, Toast.LENGTH_SHORT).show()
}
