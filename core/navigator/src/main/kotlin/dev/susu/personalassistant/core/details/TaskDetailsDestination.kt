package dev.susu.personalassistant.core.details

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dev.susu.personalassistant.core.navigator.PerAssNavigationDestination

object TaskDetailsDestination : PerAssNavigationDestination {

    private const val DEFAULT_PARAM = Int.MIN_VALUE

    override fun route(): String = TASK_DETAILS_BOTTOM_NAV_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(TASK_ID_PARAM) {
            type = NavType.IntType
        })

    const val TASK_ID_PARAM = "task"

    private const val TASK_DETAILS_ROUTE = "task_details"
    private const val TASK_DETAILS_BOTTOM_NAV_ROUTE = "$TASK_DETAILS_ROUTE/{$TASK_ID_PARAM}"
    fun createTaskDetailsRoute(taskId: Int = DEFAULT_PARAM) = "$TASK_DETAILS_ROUTE/${taskId}"

    override val enterTransition: (AnimatedContentScope.() -> EnterTransition?)?
        get() = super.enterTransition

    override val exitTransition: (AnimatedContentScope.() -> ExitTransition?)?
        get() = super.exitTransition

    override val popEnterTransition: (AnimatedContentScope.() -> EnterTransition?)?
        get() = super.popEnterTransition

    override val popExitTransition: (AnimatedContentScope.() -> ExitTransition?)?
        get() = super.popExitTransition
}