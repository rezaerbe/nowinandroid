package com.erbe.nowinandroid.data.article.data.repository

import com.erbe.nowinandroid.data.article.data.expected.listArticleContentTopic
import com.erbe.nowinandroid.data.article.data.util.DataTest
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSourceImpl
import com.erbe.nowinandroid.data.article.network.expected.listArticleContentTopicResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ArticleLatestRepositoryTest : DataTest() {

    private lateinit var articleRemoteDataSource: ArticleRemoteDataSourceImpl
    private lateinit var articleRepository: ArticleRepositoryImpl

    @Before
    fun initRepository() {
        articleRemoteDataSource = mock()
        articleRepository =
            ArticleRepositoryImpl(articleRemoteDataSource, dispatcherRule.testDispatcher)
    }

    @Test
    fun articleRepositoryTest() = runTest {
        whenever(articleRemoteDataSource.getArticleLatest())
            .thenReturn(listArticleContentTopicResponse)
        val actual = articleRepository.getArticleLatest()

        Assert.assertEquals(listArticleContentTopic, actual)
    }
}