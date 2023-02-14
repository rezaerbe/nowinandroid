package com.erbe.nowinandroid.data.article.data.di

import com.erbe.nowinandroid.data.article.data.repository.ArticleRepository
import com.erbe.nowinandroid.data.article.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ArticleDataModule {

    @Binds
    abstract fun bindArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl
    ): ArticleRepository
}