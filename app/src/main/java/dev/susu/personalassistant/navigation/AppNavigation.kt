package dev.susu.personalassistant.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.susu.personalassistant.core.details.TaskDetailsDestination
import dev.susu.personalassistant.core.home.HomeDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigationDestination
import dev.susu.personalassistant.feature.home.ui.HomeScreen
import dev.susu.personalassistant.task_details.ui.TaskDetailsScreen


private val composableDestinations: Map<PerAssNavigationDestination, @Composable () -> Unit> =
    mapOf(
        HomeDestination to { HomeScreen() },
        TaskDetailsDestination to { TaskDetailsScreen() }
    )

fun NavGraphBuilder.addComposableDestinations(navController: NavHostController) {
    composableDestinations.forEach { entry ->
        val destination = entry.key
        composable(destination.route(), destination.arguments, destination.deepLinks) {
            entry.value()
        }
    }
}