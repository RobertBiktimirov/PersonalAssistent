package dev.susu.personalassistant.feature.home.ui

import dev.susu.personalassistant.core.ui.screenHelpers.Action

internal sealed interface HomeScreenAction : Action {

    data class OnTaskDetails(val taskId: Int? = null): HomeScreenAction
}