package dev.susu.personalassistant.core.database.room.converters

import androidx.room.TypeConverter
import dev.susu.personalassistant.core.database.room.entity.TaskProgressEntity

internal class TaskProgressConverter {

    @TypeConverter
    fun toProgress(value: String) = enumValueOf<TaskProgressEntity>(value)

    @TypeConverter
    fun fromProgress(value: TaskProgressEntity) = value.name
}