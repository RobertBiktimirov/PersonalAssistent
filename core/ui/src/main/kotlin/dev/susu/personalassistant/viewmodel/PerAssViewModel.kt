package dev.susu.personalassistant.viewmodel

import androidx.lifecycle.ViewModel
import dev.susu.personalassistant.screenHelpers.Action
import dev.susu.personalassistant.screenHelpers.ScreenState
import kotlinx.coroutines.flow.StateFlow

abstract class PerAssViewModel <state: ScreenState, action: Action>: ViewModel() {

    abstract val screenState: StateFlow<state>

    abstract fun onAction(action: action)
}