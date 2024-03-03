package dev.susu.personalassistant.feature.home.ui

import dev.susu.personalassistant.screenHelpers.Action

sealed interface HomeScreenAction : Action {

    data object TODO: HomeScreenAction
}