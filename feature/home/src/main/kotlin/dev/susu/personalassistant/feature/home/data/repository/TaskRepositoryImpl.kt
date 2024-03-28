package dev.susu.personalassistant.feature.home.data.repository

import dev.susu.personalassistant.core.database.room.dao.TasksDao
import dev.susu.personalassistant.core.database.sp.user.UserStorage
import dev.susu.personalassistant.feature.home.data.mappers.models
import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel
import dev.susu.personalassistant.feature.home.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TaskRepositoryImpl @Inject constructor(
    private val tasksDao: TasksDao,
    private val userStorage: UserStorage
) : TaskRepository {

    override fun getTasks(): Flow<List<TaskItemModel>> = tasksDao.getTaskList().map { it.models }

    override suspend fun getUserName(): String? = userStorage.userName
}