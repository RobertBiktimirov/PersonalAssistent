package dev.susu.personalassistant.feature.home.domain.repository

import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel
import kotlinx.coroutines.flow.Flow

internal interface TaskRepository {

    fun getTasks(): Flow<List<TaskItemModel>>

    suspend fun getUserName(): String?
}