package dev.susu.personalassistant.core.database.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dev.susu.personalassistant.core.database.sp.user.UserStorage
import dev.susu.personalassistant.core.database.sp.user.UserStorageImpl

@Module
@InstallIn(SingletonComponent::class)
interface UserStorageModule {

    @Binds
    fun bindUserStorage(impl: UserStorageImpl): UserStorage
}