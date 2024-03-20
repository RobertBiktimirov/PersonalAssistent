package dev.susu.personalassistant

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.susu.personalassistant.core.home.HomeDestination
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.core.navigator.PerAssNavigatorEvent
import dev.susu.personalassistant.feature.home.ui.HomeScreen
import dev.susu.personalassistant.navigation.addComposableDestinations
import dev.susu.personalassistant.core.ui.theme.BackgroundGradient
import dev.susu.personalassistant.ui.theme.PersonalAssistantTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: PerAssNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PersonalAssistantTheme {
                PerAssScaffold(navigator = navigator)
            }
        }
    }
}

@Composable
fun PerAssScaffold(navigator: PerAssNavigator) {
    val navController = rememberNavController()
    LaunchedEffect(navController) {
        navigator.destinations.collect { event ->
            when (event) {
                is PerAssNavigatorEvent.Directions -> navController.navigate(
                    event.destination,
                    event.builder
                )

                PerAssNavigatorEvent.NavigateUp -> navController.navigateUp()
                PerAssNavigatorEvent.PopBackStack -> navController.popBackStack()
            }
        }
    }


    Scaffold {
        Surface(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = HomeDestination.route(),
                enterTransition = { fadeIn(animationSpec = tween(0)) },
                exitTransition = { fadeOut(animationSpec = tween(0)) },
            ) {
                addComposableDestinations(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PersonalAssistantTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen()
        }
    }
}