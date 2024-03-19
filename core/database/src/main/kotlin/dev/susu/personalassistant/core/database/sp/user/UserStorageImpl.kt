package dev.susu.personalassistant.core.database.sp.user

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val SHARED_PREFERENCE_NAME = "shared preference name"
private const val SHARED_PREFERENCE_USER_NAME_KEY = "user name"

class UserStorageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : UserStorage {

    private val storage = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val edit = storage.edit()

    override val userName: String?
        get() = storage.getString(SHARED_PREFERENCE_USER_NAME_KEY, null)

    override suspend fun saveUserName(name: String): Boolean {
       return edit.putString(SHARED_PREFERENCE_USER_NAME_KEY, name).commit()
    }
}