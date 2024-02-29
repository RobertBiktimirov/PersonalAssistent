package dev.susu.personalassistant.ui

import dev.susu.personalassistant.screenHelpers.Action

sealed interface HomeScreenAction : Action {

    data object TODO: HomeScreenAction
}