package dev.susu.personalassistant.core.navigator

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

interface PerAssNavigator {

    fun navigateUp(): Boolean
    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean

    fun popBackStack(): Boolean

    val destinations: Flow<PerAssNavigatorEvent>
}

@Singleton
internal class PerAssNavigatorImpl @Inject constructor() : PerAssNavigator {

    private val navigationEvents = Channel<PerAssNavigatorEvent>(Channel.BUFFERED)
    override val destinations: Flow<PerAssNavigatorEvent> = navigationEvents.receiveAsFlow()

    override fun navigateUp(): Boolean =
        navigationEvents.trySend(PerAssNavigatorEvent.NavigateUp).isSuccess

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit): Boolean =
        navigationEvents.trySend(PerAssNavigatorEvent.Directions(route, builder)).isSuccess

    override fun popBackStack(): Boolean =
        navigationEvents.trySend(PerAssNavigatorEvent.PopBackStack).isSuccess
}