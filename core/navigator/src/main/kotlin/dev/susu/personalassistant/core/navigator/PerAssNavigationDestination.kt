package dev.susu.personalassistant.core.navigator

import androidx.navigation.NamedNavArgument

fun interface PerAssNavigationDestination {

    fun route(): String
    val arguments: List<NamedNavArgument>
        get() = emptyList()
}