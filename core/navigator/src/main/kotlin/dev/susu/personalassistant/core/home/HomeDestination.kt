package dev.susu.personalassistant.core.home

import dev.susu.personalassistant.core.navigator.PerAssNavigationDestination

object HomeDestination : PerAssNavigationDestination {

    private const val HOME_ROUTE = "home route"

    override fun route(): String = HOME_ROUTE
}