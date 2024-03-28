package dev.susu.personalassistant.feature.home.data.mappers

import dev.susu.personalassistant.core.database.room.entity.TaskEntity
import dev.susu.personalassistant.core.database.room.entity.TaskProgressEntity
import dev.susu.personalassistant.core.database.room.entity.TaskProgressEntity.*
import dev.susu.personalassistant.core.database.room.entity.TaskTypeEntity
import dev.susu.personalassistant.feature.home.domain.models.TaskItemModel
import dev.susu.personalassistant.feature.home.domain.models.TaskProgress
import dev.susu.personalassistant.feature.home.domain.models.TaskType

private val TaskProgressEntity.model: TaskProgress
    get() = when (this) {
        IN_PROGRESS -> TaskProgress.IN_PROGRESS
        NEW_TASK -> TaskProgress.NEW_TASK
        COMPLETED -> TaskProgress.COMPLETED
    }

private val TaskTypeEntity.toModel: TaskType
    get() = TaskType(color, progress.model)

val TaskEntity.model: TaskItemModel
    get() = TaskItemModel(
        title,
        description,
        type.toModel,
        deadline,
        id
    )

val List<TaskEntity>.models: List<TaskItemModel>
    get() = map { it.model }