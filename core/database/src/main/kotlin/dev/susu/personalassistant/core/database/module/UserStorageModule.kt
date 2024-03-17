package dev.susu.personalassistant.core.database.module

import dagger.Binds
import dagger.Module
import dev.susu.personalassistant.core.database.sp.UserStorage
import dev.susu.personalassistant.core.database.sp.UserStorageImpl

@Module
interface UserStorageModule {

    @Binds
    fun bindUserStorage(impl: UserStorageImpl): UserStorage
}