package dev.susu.personalassistant.feature.home.ui

import androidx.compose.runtime.Stable
import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue
import dev.susu.personalassistant.screenHelpers.ScreenState

@Stable
internal sealed interface HomeScreenState : ScreenState {

    data object Loading: HomeScreenState

    data class Success(val data: HomeScreenValue): HomeScreenState

    data object Error: HomeScreenState
}