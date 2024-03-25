package dev.susu.personalassistant.task_details.ui

import androidx.compose.runtime.Stable
import dev.susu.personalassistant.core.ui.screenHelpers.ScreenState

@Stable
internal sealed interface TaskDetailsScreenState : ScreenState {

    data object Error : TaskDetailsScreenState
    data object Loading : TaskDetailsScreenState
    data class Success(val value: TaskDetailsValue? = null) : TaskDetailsScreenState
}

@Stable
internal data class TaskDetailsValue(
    val id: Int,
    val description: String
)