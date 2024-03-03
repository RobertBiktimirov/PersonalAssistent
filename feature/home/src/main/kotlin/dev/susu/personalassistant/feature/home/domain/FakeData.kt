package dev.susu.personalassistant.feature.home.domain

import androidx.compose.ui.graphics.Color
import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue

object FakeData {

    private const val NOW_DATE = "21 Sept, 2024"
    private const val USER_NAME: String = "Robert"


    private val taskList: List<TaskItem> = buildList {
        for (i in 0..5) {
            val item = TaskItem(
                title = "Homepage Redesign",
                description = "Redesign the homepage of our website to improve user engagement and align with our updated branding guidelines. Focus on creating an intuitive user interface with enhanced visual appeal.",
                type = TaskType(Color.Green, TaskProgress.entries.random()),
                deadline = "October 15, 2023"
            )

            add(item)
        }
    }

    private val assignedTasks =
        taskList.filter { it.type.progress != TaskProgress.COMPLETED }.size.toString()
    private val completedTasks =
        taskList.filter { it.type.progress == TaskProgress.COMPLETED }.size.toString()
    val homeScreenData = HomeScreenValue(USER_NAME, NOW_DATE, assignedTasks, completedTasks, taskList)
}

data class TaskItem(
    val title: String,
    val description: String,
    val type: TaskType,
    val deadline: String,
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