package dev.susu.personalassistant.feature.home.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.viewmodel.MyViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : MyViewModel<HomeScreenState, HomeScreenAction>() {

    private val _screenState = Channel<HomeScreenState>(Channel.BUFFERED)
    override val screenState: StateFlow<HomeScreenState>
        get() = _screenState.receiveAsFlow() as StateFlow<HomeScreenState>

    override fun onAction(action: HomeScreenAction) = when(action){
        HomeScreenAction.TODO -> TODO()
    }
}