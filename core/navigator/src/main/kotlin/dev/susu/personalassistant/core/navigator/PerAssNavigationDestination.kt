package dev.susu.personalassistant.core.navigator

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

fun interface PerAssNavigationDestination {

    fun route(): String

    val deepLinks: List<NavDeepLink>
        get() = emptyList()

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val enterTransition: (AnimatedContentScope.() -> EnterTransition?)?
        get() = null

    val exitTransition: (AnimatedContentScope.() -> ExitTransition?)?
        get() = null

    val popEnterTransition: (AnimatedContentScope.() -> EnterTransition?)?
        get() = enterTransition

    val popExitTransition: (AnimatedContentScope.() -> ExitTransition?)?
        get() = exitTransition

    val dialogProperties: DialogProperties
        get() = DialogProperties()
}