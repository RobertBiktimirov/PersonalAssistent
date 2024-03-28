package dev.susu.personalassistant.task_details.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.susu.personalassistant.task_details.data.TaskDetailsRepositoryImpl
import dev.susu.personalassistant.task_details.domain.repository.TaskDetailsRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface TaskDetailsModule {

    @Binds
    fun bindTaskDetailsRepository(impl: TaskDetailsRepositoryImpl): TaskDetailsRepository
}