package dev.susu.personalassistant.task_details.ui

import dev.susu.personalassistant.core.ui.screenHelpers.Action

internal sealed interface TaskDetailsAction : Action {

    data object ToBack : TaskDetailsAction

    data class SaveTask(val task: TaskDetailsValue) : TaskDetailsAction

    data class UpdateTask(val task: TaskDetailsValue) : TaskDetailsAction
}