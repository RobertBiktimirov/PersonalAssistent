package dev.susu.personalassistant.feature.home.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.core.details.TaskDetailsDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.feature.home.domain.FakeData
import dev.susu.personalassistant.core.ui.viewmodel.PerAssViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigator: PerAssNavigator
) : PerAssViewModel<HomeScreenState, HomeScreenAction>() {

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    override val screenState: StateFlow<HomeScreenState>
        get() = _screenState


    init {
        viewModelScope.launch {
            _screenState.emit(HomeScreenState.Success(FakeData.homeScreenData))
        }
    }

    override fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnTaskDetails -> {
                navigator.navigate(
                    if (action.taskId == null) TaskDetailsDestination.createTaskDetailsRoute()
                    else TaskDetailsDestination.createTaskDetailsRoute(action.taskId)
                )
            }
        }
    }
}