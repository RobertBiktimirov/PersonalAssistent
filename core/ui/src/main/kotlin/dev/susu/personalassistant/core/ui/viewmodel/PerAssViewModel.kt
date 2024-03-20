package dev.susu.personalassistant.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import dev.susu.personalassistant.core.ui.screenHelpers.Action
import dev.susu.personalassistant.core.ui.screenHelpers.ScreenState
import kotlinx.coroutines.flow.StateFlow

abstract class PerAssViewModel <state: ScreenState, action: Action>: ViewModel() {

    abstract val screenState: StateFlow<state>

    abstract fun onAction(action: action)
}