package dev.susu.personalassistant.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.screenState.collectAsStateWithLifecycle()

    when(state.value) {
        HomeScreenState.Error -> TODO()
        HomeScreenState.Loading -> TODO()
        is HomeScreenState.Success -> TODO()
    }
}

@Composable
fun HomeScreen(
    state: HomeScreenState,
    action: (HomeScreenAction) -> Unit
) {}