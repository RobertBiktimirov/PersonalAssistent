package dev.susu.personalassistant.task_details.domain.useCases

import dev.susu.personalassistant.task_details.domain.repository.TaskDetailsRepository
import dev.susu.personalassistant.task_details.ui.TaskDetailsValue
import javax.inject.Inject

internal class GetTaskDetailsUseCase @Inject constructor(
    private val repository: TaskDetailsRepository
) {
    suspend operator fun invoke(taskId: Int): Result<TaskDetailsValue> =
        repository.getTaskDetails(taskId).map {
            TaskDetailsValue(
                id = it.id,
                description = it.description,
                term = it.term,
                reminder = it.reminder,
                importance = it.importance
            )
        }
}
