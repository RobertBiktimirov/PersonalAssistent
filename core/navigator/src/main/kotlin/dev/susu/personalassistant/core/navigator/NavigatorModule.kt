package dev.susu.personalassistant.core.navigator

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigatorModule {
    @Binds
    fun bindNavigator(navigator: PerAssNavigatorImpl): PerAssNavigator
}