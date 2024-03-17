package dev.susu.personalassistant.core.navigator

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import javax.inject.Singleton

interface PerAssNavigator {

    fun navigateUp(): Boolean
    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean

    val destinations: Channel<PerAssNavigatorEvent>
}

@Singleton
internal class PerAssNavigatorImpl @Inject constructor() : PerAssNavigator {

    private val navigationEvents = Channel<PerAssNavigatorEvent>()
    override val destinations: Channel<PerAssNavigatorEvent> = navigationEvents

    override fun navigateUp(): Boolean =
        navigationEvents.trySend(PerAssNavigatorEvent.NavigateUp).isSuccess

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
        navigationEvents.trySend(PerAssNavigatorEvent.Directions(route, builder)).isSuccess

}