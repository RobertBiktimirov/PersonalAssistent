package dev.susu.personalassistant.ui

import dev.susu.personalassistant.screenHelpers.ScreenState

sealed interface HomeScreenState : ScreenState {

    data object Loading: HomeScreenState

    data class Success(val data: Any): HomeScreenState

    data object Error: HomeScreenState
}