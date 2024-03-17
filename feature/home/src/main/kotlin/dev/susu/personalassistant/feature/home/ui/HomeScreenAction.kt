package dev.susu.personalassistant.feature.home.ui

import dev.susu.personalassistant.screenHelpers.Action

internal sealed interface HomeScreenAction : Action {

    data class OnTaskDetails(val taskId: Int): HomeScreenAction
}