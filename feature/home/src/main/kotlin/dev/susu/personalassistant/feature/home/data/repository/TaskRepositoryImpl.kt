package dev.susu.personalassistant.feature.home.data.repository

import dev.susu.personalassistant.core.database.room.dao.TasksDao
import dev.susu.personalassistant.core.database.sp.UserStorage
import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel
import dev.susu.personalassistant.feature.home.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TaskRepositoryImpl @Inject constructor(
    private val tasksDao: TasksDao,
    private val userStorage: UserStorage
) : TaskRepository {

    override fun getTasks(): Flow<List<TaskItemModel>> {
        val tasks = tasksDao.getTaskList()
        TODO()
    }

    override suspend fun getUserName(): String? = userStorage.userName
}