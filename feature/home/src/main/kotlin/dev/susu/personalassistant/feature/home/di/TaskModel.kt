package dev.susu.personalassistant.feature.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dev.susu.personalassistant.feature.home.data.repository.TaskRepositoryImpl
import dev.susu.personalassistant.feature.home.domain.repository.TaskRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface TaskModel {

    @Binds
    fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}