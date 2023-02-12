package com.erbe.nowinandroid.core.common.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.erbe.nowinandroid.core.common.network.connection.ConnectionManager
import com.erbe.nowinandroid.core.common.network.connection.ConnectionManagerImpl
import com.erbe.nowinandroid.core.common.network.interceptor.ConnectionStatusInterceptor
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindConnectionManager(
        connectionManagerImpl: ConnectionManagerImpl
    ): ConnectionManager

    companion object {
        @Singleton
        @Provides
        fun provideMoshi(): Moshi {
            return Moshi.Builder().build()
        }

        @Singleton
        @Provides
        fun provideChuckerInterceptor(
            @ApplicationContext context: Context
        ): ChuckerInterceptor {
            return ChuckerInterceptor.Builder(context)
                .collector(
                    ChuckerCollector(
                        context,
                        true,
                        RetentionManager.Period.ONE_HOUR
                    )
                )
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(
            connectionStatusInterceptor: ConnectionStatusInterceptor,
            chuckerInterceptor: ChuckerInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(connectionStatusInterceptor)
                .addInterceptor(chuckerInterceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://192.168.45.125:8080")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}