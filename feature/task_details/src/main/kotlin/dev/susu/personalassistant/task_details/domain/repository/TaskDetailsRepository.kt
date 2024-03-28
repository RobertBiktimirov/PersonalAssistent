package dev.susu.personalassistant.task_details.domain.repository

import dev.susu.personalassistant.task_details.domain.models.TaskDetailsModel

internal interface TaskDetailsRepository {

    suspend fun getTaskDetails(id: Int): Result<TaskDetailsModel>

}