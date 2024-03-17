package dev.susu.personalassistant.feature.home.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.viewmodel.PerAssViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigator: PerAssNavigator
) : PerAssViewModel<HomeScreenState, HomeScreenAction>() {

    private val _screenState = Channel<HomeScreenState>(Channel.BUFFERED)
    override val screenState: StateFlow<HomeScreenState>
        get() = _screenState.receiveAsFlow() as StateFlow<HomeScreenState>

    override fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnTaskDetails -> {
                navigator.navigate("")
            }
        }
    }
}