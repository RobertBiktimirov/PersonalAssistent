package dev.susu.personalassistant.core.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.susu.personalassistant.core.database.room.converters.TaskProgressConverter

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("task_id") val id: Int,
    @ColumnInfo("task_description") val description: String,
    @Embedded val type: TaskTypeEntity,
    @ColumnInfo("task_deadline") val deadline: String,
)

@Entity
data class TaskTypeEntity(
    @ColumnInfo("task_color") val color: String,
    @TypeConverters(TaskProgressConverter::class)
    @ColumnInfo("task_progress") val progress: TaskProgressEntity
)

enum class TaskProgressEntity(val value: String) {
    IN_PROGRESS("On going"),
    NEW_TASK("New task"),
    COMPLETED("Completed")
}