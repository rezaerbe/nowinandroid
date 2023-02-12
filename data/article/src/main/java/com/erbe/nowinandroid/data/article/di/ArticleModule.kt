package com.erbe.nowinandroid.data.article.di

import com.erbe.nowinandroid.data.article.remote.datasource.ArticleRemoteDataSource
import com.erbe.nowinandroid.data.article.remote.datasource.ArticleRemoteDataSourceImpl
import com.erbe.nowinandroid.data.article.remote.service.ArticleService
import com.erbe.nowinandroid.data.article.repository.ArticleRepository
import com.erbe.nowinandroid.data.article.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleModule {

    @Binds
    abstract fun bindArticleRemoteDataSource(
        articleRemoteDataSourceImpl: ArticleRemoteDataSourceImpl
    ): ArticleRemoteDataSource

    @Binds
    abstract fun bindArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl
    ): ArticleRepository

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