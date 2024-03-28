package dev.susu.personalassistant.task_details.data

import dev.susu.personalassistant.core.database.room.dao.TasksDao
import dev.susu.personalassistant.task_details.domain.models.TaskDetailsModel
import dev.susu.personalassistant.task_details.domain.repository.TaskDetailsRepository
import dev.susu.personalassistant.task_details.ui.TaskDetailsValue
import javax.inject.Inject

internal class TaskDetailsRepositoryImpl @Inject constructor(
    private val dao: TasksDao
) : TaskDetailsRepository {
    override suspend fun getTaskDetails(id: Int): Result<TaskDetailsModel> {
        TODO("Not yet implemented")
    }
}