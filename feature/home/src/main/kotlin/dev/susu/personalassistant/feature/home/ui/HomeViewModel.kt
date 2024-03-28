package dev.susu.personalassistant.feature.home.ui

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptionsBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.core.details.TaskDetailsDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.feature.home.domain.FakeData
import dev.susu.personalassistant.core.ui.viewmodel.PerAssViewModel
import dev.susu.personalassistant.feature.home.domain.models.TaskProgress
import dev.susu.personalassistant.feature.home.domain.repository.TaskRepository
import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigator: PerAssNavigator,
    private val taskRepository: TaskRepository
) : PerAssViewModel<HomeScreenState, HomeScreenAction>() {

    private val _screenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    override val screenState: StateFlow<HomeScreenState>
        get() = _screenState

    private val dateFormat = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault())

    private val dateNow
        get() = Calendar.getInstance().time

    init {
        viewModelScope.launch {
            getTaskListValue()
        }
    }

    private suspend fun getTaskListValue() {
        taskRepository.getTasks().collect { tasks ->
            val userName = taskRepository.getUserName() ?: ""
            val dateNow = dateFormat.format(dateNow)
            val assignedTasks = tasks.size
            val completedTasks =
                tasks.filter { it.type.progress == TaskProgress.COMPLETED }.size
            _screenState.emit(
                HomeScreenState.Success(
                    HomeScreenValue(
                        userName,
                        dateNow,
                        assignedTasks.toString(),
                        completedTasks.toString(),
                        tasks
                    )
                )
            )
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