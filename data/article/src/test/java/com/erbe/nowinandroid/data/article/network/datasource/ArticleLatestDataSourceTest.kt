package com.erbe.nowinandroid.data.article.network.datasource

import com.erbe.nowinandroid.data.article.network.expected.listArticleContentTopicResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleContentTopicResponse
import com.erbe.nowinandroid.data.article.network.util.ArticleNetworkTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ArticleLatestDataSourceTest : ArticleNetworkTest() {

    override suspend fun dataSourceCall() = articleRemoteDataSource.getArticleLatest()

    @Test
    fun articleLatestEmptyTest() = runTest {
        enqueueResponse("article_latest/article_latest_empty.json", 200)
        val actual = dataSourceCall()
        Assert.assertEquals(emptyList<ArticleContentTopicResponse>(), actual)
    }

    @Test
    fun articleLatestSuccessTest() = runTest {
        enqueueResponse("article_latest/article_latest.json", 200)
        val actual = dataSourceCall()
        Assert.assertEquals(listArticleContentTopicResponse, actual)
    }
}