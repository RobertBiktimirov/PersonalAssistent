package dev.susu.personalassistant.task_details.ui

import dev.susu.personalassistant.screenHelpers.Action

internal sealed interface TaskDetailsAction : Action {

    data object ToBack : TaskDetailsAction
}