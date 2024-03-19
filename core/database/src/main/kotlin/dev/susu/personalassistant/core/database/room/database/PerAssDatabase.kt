package dev.susu.personalassistant.core.database.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.susu.personalassistant.core.database.room.dao.TasksDao
import dev.susu.personalassistant.core.database.room.entity.TaskEntity

internal const val TASK_DATABASE_NAME = "tasks database"

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class PerAssDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}