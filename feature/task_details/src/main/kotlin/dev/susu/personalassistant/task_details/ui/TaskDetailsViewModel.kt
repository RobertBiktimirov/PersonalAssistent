package dev.susu.personalassistant.task_details.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.core.details.TaskDetailsDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.core.ui.viewmodel.PerAssViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class TaskDetailsViewModel @Inject constructor(
    private val navigator: PerAssNavigator,
    savedStateHandle: SavedStateHandle
) : PerAssViewModel<TaskDetailsScreenState, TaskDetailsAction>(), PerAssNavigator by navigator {

    private val _screenState = MutableStateFlow(TaskDetailsScreenState.Loading)
    override val screenState: StateFlow<TaskDetailsScreenState> = _screenState.asStateFlow()

    private val id = savedStateHandle.get<Int>(TaskDetailsDestination.TASK_ID_PARAM)

    init {

    }


    override fun onAction(action: TaskDetailsAction) {
        when (action) {
            TaskDetailsAction.ToBack -> navigator.popBackStack()
            is TaskDetailsAction.SaveTask -> TODO()
            is TaskDetailsAction.UpdateTask -> TODO()
        }
    }
}