package dev.susu.personalassistant.feature.home.ui

import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue
import dev.susu.personalassistant.screenHelpers.ScreenState

sealed interface HomeScreenState : ScreenState {

    data object Loading: HomeScreenState

    data class Success(val data: HomeScreenValue): HomeScreenState

    data object Error: HomeScreenState
}