package com.erbe.nowinandroid.data.article.dispatcher

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.dispatcher.DispatcherModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatcherModule::class]
)
object TestDispatchersModule {

    @Provides
    @Dispatcher(AppDispatcher.IO)
    fun provideIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher =
        testDispatcher

    @Provides
    @Dispatcher(AppDispatcher.Default)
    fun provideDefaultDispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher =
        testDispatcher
}