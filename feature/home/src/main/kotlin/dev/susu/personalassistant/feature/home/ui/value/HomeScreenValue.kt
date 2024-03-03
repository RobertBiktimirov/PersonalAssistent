package dev.susu.personalassistant.feature.home.ui.value

import androidx.compose.runtime.Stable
import dev.susu.personalassistant.feature.home.domain.TaskItem

@Stable
data class HomeScreenValue(
    val userName: String,
    val date: String,
    val assignedTasks: String,
    val completedTasks: String,
    val tasks: List<TaskItem>
)