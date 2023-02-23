package com.erbe.nowinandroid.data.article.dispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDispatcherModule {

    @Singleton
    @Provides
    fun provideTestDispatcher(): TestDispatcher = UnconfinedTestDispatcher()
}