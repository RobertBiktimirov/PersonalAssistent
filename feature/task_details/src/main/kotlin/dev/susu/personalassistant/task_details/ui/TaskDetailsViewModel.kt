package dev.susu.personalassistant.task_details.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.core.details.TaskDetailsDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.core.ui.viewmodel.PerAssViewModel
import dev.susu.personalassistant.task_details.domain.useCases.GetTaskDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TaskDetailsViewModel @Inject constructor(
    private val navigator: PerAssNavigator,
    savedStateHandle: SavedStateHandle,
    private val getTaskDetailsUseCase: GetTaskDetailsUseCase
) : PerAssViewModel<TaskDetailsScreenState, TaskDetailsAction>() {

    private val _screenState: MutableStateFlow<TaskDetailsScreenState> =
        MutableStateFlow(TaskDetailsScreenState.Loading)
    override val screenState: StateFlow<TaskDetailsScreenState> = _screenState.asStateFlow()

    private val id =
        savedStateHandle.get<Int>(TaskDetailsDestination.TASK_ID_PARAM) ?: run {
            Log.d("ROBBIK", "error id = null")
            _screenState.tryEmit(TaskDetailsScreenState.Error)
            error("id = null")
        }

    init {
        viewModelScope.launch {
            if (id == TaskDetailsDestination.defaultParam) {
                _screenState.emit(TaskDetailsScreenState.Success())
                return@launch
            }

            getTaskDetailsUseCase(id).fold(
                onSuccess = { _screenState.emit(TaskDetailsScreenState.Success(it)) },
                onFailure = { _screenState.emit(TaskDetailsScreenState.Error) }
            )
        }

    }


    override fun onAction(action: TaskDetailsAction) {
        when (action) {
            TaskDetailsAction.ToBack -> navigator.popBackStack()
            is TaskDetailsAction.SaveTask -> {}
            is TaskDetailsAction.UpdateTask -> {}
        }
    }
}