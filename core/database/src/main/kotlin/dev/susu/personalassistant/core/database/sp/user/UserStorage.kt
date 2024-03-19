package dev.susu.personalassistant.core.database.sp.user

interface UserStorage {

    val userName: String?

    suspend fun saveUserName(name: String): Boolean
}