package com.erbe.nowinandroid.remoteconfig.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    companion object {
        @Singleton
        @Provides
        fun provideRemoteConfig(): FirebaseRemoteConfig {
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 1
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            return remoteConfig
        }

        @Singleton
        @Provides
        fun provideFirebaseAnalytics(): FirebaseAnalytics {
            return Firebase.analytics
        }

        @Singleton
        @Provides
        fun provideFirebaseCrashlytics(): FirebaseCrashlytics {
            return Firebase.crashlytics
        }
    }
}