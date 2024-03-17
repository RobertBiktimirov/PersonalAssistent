package dev.susu.personalassistant.core.database.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.susu.personalassistant.core.database.room.dao.TasksDao
import dev.susu.personalassistant.core.database.room.database.PerAssDatabase
import dev.susu.personalassistant.core.database.room.database.TASK_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePerAssDatabase(
        @ApplicationContext context: Context
    ): PerAssDatabase =
        Room.databaseBuilder(
            context,
            PerAssDatabase::class.java,
            TASK_DATABASE_NAME
        ).build()


    @Provides
    fun provideTasksDao(database: PerAssDatabase): TasksDao {
        return database.tasksDao()
    }
}