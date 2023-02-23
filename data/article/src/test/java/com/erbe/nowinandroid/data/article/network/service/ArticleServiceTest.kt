package com.erbe.nowinandroid.data.article.network.service

import com.erbe.nowinandroid.core.common.network.model.Items
import com.erbe.nowinandroid.core.common.network.model.NetworkResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleContentTopicResponse
import com.erbe.nowinandroid.data.article.network.model.ArticleTopicResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArticleServiceTest : ServiceTest<ArticleService>() {

    private lateinit var service: ArticleService

    @Before
    fun initService() {
        service = createService(ArticleService::class.java)
    }

    @Test
    fun articleServiceTest() = runTest {
        enqueueResponse("article_latest.json", 200)
        val actual = service.getArticleLatest()

        Assert.assertEquals(
            NetworkResponse(
                200,
                "OK",
                Items(
                    listOf(
                        ArticleContentTopicResponse(
                            id = "1",
                            tag = "now-in-android",
                            title = "Now in Android #75",
                            subtitle = "Android Studio Electric Eel, Architecture, Kotlin Multiplatform, WearOS, CameraX, Stylus, and more.",
                            content = "http://192.168.183.125:8080/static/article/index.html",
                            image = "https://miro.medium.com/0*IdylXqMS2sF3Nh30",
                            date = "2023-01-19",
                            time = "4",
                            topic = ArticleTopicResponse(
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
                    )
                )
            ),
            actual
        )
    }
}