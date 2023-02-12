package com.erbe.nowinandroid.core.firebase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticModule {

    @Binds
    abstract fun bindAnalytic(
        analyticImpl: AnalyticImpl
    ): Analytic
}