package dev.susu.personalassistant.task_details.ui

import androidx.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.susu.personalassistant.core.navigator.PerAssNavigator
import dev.susu.personalassistant.viewmodel.PerAssViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
internal class TaskDetailsViewModel @Inject constructor(
    private val navigator: PerAssNavigator
) : PerAssViewModel<TaskDetailsScreenState, TaskDetailsAction>() {
    override val screenState: StateFlow<TaskDetailsScreenState>
        get() = TODO("Not yet implemented")

    override fun onAction(action: TaskDetailsAction) {
        when (action) {
            TaskDetailsAction.ToBack -> navigator.popBackStack()
        }
    }
}