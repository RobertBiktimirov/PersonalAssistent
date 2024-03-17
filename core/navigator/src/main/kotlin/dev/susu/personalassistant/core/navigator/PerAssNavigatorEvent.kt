package dev.susu.personalassistant.core.navigator

import androidx.navigation.NavOptionsBuilder

sealed class PerAssNavigatorEvent {
    data object NavigateUp : PerAssNavigatorEvent()
    class Directions(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit
    ) : PerAssNavigatorEvent()

    data object PopBackStack : PerAssNavigatorEvent()
}