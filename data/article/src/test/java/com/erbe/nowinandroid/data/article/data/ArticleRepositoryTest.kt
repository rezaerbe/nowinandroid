package com.erbe.nowinandroid.data.article.data

import com.erbe.nowinandroid.data.article.data.model.ArticleContentTopic
import com.erbe.nowinandroid.data.article.data.model.ArticleTopic
import com.erbe.nowinandroid.data.article.data.repository.ArticleRepositoryImpl
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSourceImpl
import com.erbe.nowinandroid.data.article.network.service.ArticleService
import com.erbe.nowinandroid.data.article.network.service.ServiceTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArticleRepositoryTest : ServiceTest<ArticleService>() {

    private lateinit var service: ArticleService
    private lateinit var articleRemoteDataSource: ArticleRemoteDataSourceImpl
    private lateinit var articleRepository: ArticleRepositoryImpl

    @Before
    fun initRepository() {
        service = createService(ArticleService::class.java)
        articleRemoteDataSource =
            ArticleRemoteDataSourceImpl(service, dispatcherRule.testDispatcher)
        articleRepository =
            ArticleRepositoryImpl(articleRemoteDataSource, dispatcherRule.testDispatcher)
    }

    @Test
    fun articleRepositoryTest() = runTest {
        enqueueResponse("article_latest.json", 200)
        val actual = articleRepository.getArticleLatest()

        Assert.assertEquals(
            listOf(
                ArticleContentTopic(
                    id = "1",
                    tag = "now-in-android",
                    title = "Now in Android #75",
                    subtitle = "Android Studio Electric Eel, Architecture, Kotlin Multiplatform, WearOS, CameraX, Stylus, and more.",
                    content = "http://192.168.183.125:8080/static/article/index.html",
                    image = "https://miro.medium.com/0*IdylXqMS2sF3Nh30",
                    date = "2023-01-19",
                    time = "4",
                    topic = ArticleTopic(
                        id = "1",
                        tag = "now-in-android",
                        name = "Now In Android",
                        description = "",
                        image = "",
                        total = "",
                        url = ""
                    ),
                    url = "https://medium.com/androiddevelopers/now-in-android-75-e4bbe977d33f"
                )
            ),
            actual
        )
    }
}