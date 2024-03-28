package dev.susu.personalassistant.feature.home.ui.value

import androidx.compose.runtime.Stable
import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel

@Stable
internal data class HomeScreenValue(
    var userName: String,
    var date: String,
    var assignedTasks: String,
    var completedTasks: String,
    var tasks: List<TaskItemModel>
)