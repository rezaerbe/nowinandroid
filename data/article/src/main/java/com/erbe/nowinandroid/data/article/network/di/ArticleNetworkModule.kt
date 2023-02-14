package com.erbe.nowinandroid.data.article.network.di

import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSource
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSourceImpl
import com.erbe.nowinandroid.data.article.network.service.ArticleService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleNetworkModule {

    @Binds
    abstract fun bindArticleRemoteDataSource(
        articleRemoteDataSourceImpl: ArticleRemoteDataSourceImpl
    ): ArticleRemoteDataSource

    companion object {
        @Singleton
        @Provides
        fun provideArticleService(
            retrofit: Retrofit
        ): ArticleService {
            return retrofit.create(ArticleService::class.java)
        }
    }
}