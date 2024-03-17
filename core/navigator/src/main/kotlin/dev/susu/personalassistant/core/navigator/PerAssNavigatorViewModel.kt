package dev.susu.personalassistant.core.navigator

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PerAssNavigatorViewModel @Inject constructor(
    private val perAssNavigator: PerAssNavigator
) : ViewModel(), PerAssNavigator by perAssNavigator