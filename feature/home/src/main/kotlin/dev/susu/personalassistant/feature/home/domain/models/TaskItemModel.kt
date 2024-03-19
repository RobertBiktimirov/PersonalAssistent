package dev.susu.personalassistant.feature.home.domain.models

import androidx.compose.ui.graphics.Color

data class TaskItemModel(
    val title: String,
    val description: String,
    val type: TaskType,
    val deadline: String,
    val id: Int,
)

data class TaskType(
    val color: Color,
    val progress: TaskProgress
)

enum class TaskProgress(val value: String) {
    IN_PROGRESS("On going"),
    NEW_TASK("New task"),
    COMPLETED("Completed")
}