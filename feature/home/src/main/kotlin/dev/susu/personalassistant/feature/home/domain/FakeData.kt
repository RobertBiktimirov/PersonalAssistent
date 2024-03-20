package dev.susu.personalassistant.feature.home.domain

import androidx.compose.ui.graphics.Color
import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel
import dev.susu.personalassistant.feature.home.domain.models.TaskProgress
import dev.susu.personalassistant.feature.home.domain.models.TaskType
import dev.susu.personalassistant.feature.home.ui.value.HomeScreenValue
import kotlin.random.Random

internal object FakeData {

    private const val NOW_DATE = "21 Sept, 2024"
    private const val USER_NAME: String = "Robert"


    private val taskList: List<TaskItemModel> = buildList<TaskItemModel> {
        for (i in 0..5) {
            val item = TaskItemModel(
                title = "Homepage Redesign",
                description = "Redesign the homepage of our website to improve user engagement and align with our updated branding guidelines. Focus on creating an intuitive user interface with enhanced visual appeal.",
                type = TaskType(Color.Green, TaskProgress.entries.random()),
                deadline = "October 15, 2023",
                Random.nextInt()
            )

            add(item)
        }
    }

    private val assignedTasks =
        taskList.filter { it.type.progress != TaskProgress.COMPLETED }.size.toString()
    private val completedTasks =
        taskList.filter { it.type.progress == TaskProgress.COMPLETED }.size.toString()
    val homeScreenData =
        HomeScreenValue(Int.MAX_VALUE, USER_NAME, NOW_DATE, assignedTasks, completedTasks, taskList)
}