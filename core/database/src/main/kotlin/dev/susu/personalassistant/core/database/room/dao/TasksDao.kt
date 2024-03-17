package dev.susu.personalassistant.core.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.susu.personalassistant.core.database.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("select * from tasks")
    fun getTaskList(): Flow<List<TaskEntity>>

    @Query("select * from tasks where task_id = :taskId")
    suspend fun getTaskItem(taskId: Int): TaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(taskEntity: TaskEntity)

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

}