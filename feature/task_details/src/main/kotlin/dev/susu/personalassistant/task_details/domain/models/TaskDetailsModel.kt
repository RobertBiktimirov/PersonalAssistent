package dev.susu.personalassistant.task_details.domain.models

internal data class TaskDetailsModel(
    val id: Int,
    val description: String,
    val term: String,
    val reminder: String,
    val importance: String
)
