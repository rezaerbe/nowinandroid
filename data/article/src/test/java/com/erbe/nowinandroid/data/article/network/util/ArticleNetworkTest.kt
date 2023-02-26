package com.erbe.nowinandroid.data.article.network.util

import com.erbe.nowinandroid.core.common.network.model.ApiException
import com.erbe.nowinandroid.core.common.network.model.EmptyException
import com.erbe.nowinandroid.core.common.network.model.NetworkException
import com.erbe.nowinandroid.core.common.network.model.ParsingException
import com.erbe.nowinandroid.data.article.network.datasource.ArticleRemoteDataSourceImpl
import com.erbe.nowinandroid.data.article.network.service.ArticleService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.SocketPolicy
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

abstract class ArticleNetworkTest : NetworkTest() {

    private lateinit var service: ArticleService
    lateinit var articleRemoteDataSource: ArticleRemoteDataSourceImpl

    @Before
    fun initDataSource() {
        service = createService(ArticleService::class.java)
        articleRemoteDataSource =
            ArticleRemoteDataSourceImpl(service, dispatcherRule.testDispatcher)
    }

    abstract suspend fun dataSourceCall(): Any

    @Test
    fun nullTest() = runTest {
        enqueueResponse("error/null.json", 200)
        try {
            dataSourceCall()
        } catch (error: Throwable) {
            Assert.assertEquals(EmptyException("Network null"), error)
        }
    }

    @Test
    fun notFoundTest() = runTest {
        enqueueResponse("error/not_found.json", 404)
        try {
            dataSourceCall()
        } catch (error: Throwable) {
            if (error is HttpException) {
                Assert.assertEquals(EmptyException(error.message()), error)
            }
        }
    }

    @Test
    fun internalServerErrorTest() = runTest {
        enqueueResponse("error/internal_server_error.json", 500)
        try {
            dataSourceCall()
        } catch (error: Throwable) {
            if (error is HttpException) {
                Assert.assertEquals(ApiException(error.code(), error.message()), error)
            }
        }
    }

    @Test
    fun endOfInputTest() = runTest {
        enqueueResponse("error/end_of_input.json", 200)
        try {
            dataSourceCall()
        } catch (error: Throwable) {
            Assert.assertEquals(ParsingException(error.message ?: "Parsing error"), error)
        }
    }

    @Test
    fun parsingTest() = runTest {
        enqueueResponse("error/parsing.json", 200)
        try {
            dataSourceCall()
        } catch (error: Throwable) {
            Assert.assertEquals(ParsingException(error.message ?: "Parsing error"), error)
        }
    }

    @Test
    fun noConnectionTest() = runTest {
        // Use SocketPolicy.NO_RESPONSE to test timeout
        enqueueResponse("error/internal_server_error.json", 500, SocketPolicy.DISCONNECT_AT_START)
        try {
            dataSourceCall()
        } catch (error: Throwable) {
            Assert.assertEquals(NetworkException(error.message ?: "Connection error"), error)
        }
    }
}