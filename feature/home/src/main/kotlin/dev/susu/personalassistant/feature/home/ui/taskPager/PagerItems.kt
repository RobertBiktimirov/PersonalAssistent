package dev.susu.personalassistant.feature.home.ui.taskPager

enum class PagerItems(val value: String, val number: Int) {

    ALL("All tasks", 1),
    IN_PROGRESS("In progress", 2),
    COMPLETED("Completed", 3)
}