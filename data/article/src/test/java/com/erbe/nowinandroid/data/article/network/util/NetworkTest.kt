package com.erbe.nowinandroid.data.article.network.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.erbe.nowinandroid.data.article.dispatcher.MainDispatcherRule
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

@RunWith(JUnit4::class)
abstract class NetworkTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun startServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    fun <T> createService(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(clazz)
    }

    fun enqueueResponse(
        fileName: String,
        code: Int,
        socketPolicy: SocketPolicy? = null
    ) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
        val inputStreamReader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
        val body = inputStreamReader.readText()

        val mockResponse = MockResponse()
            .setBody(body)
            .setResponseCode(code)

        socketPolicy?.let {
            mockResponse.setSocketPolicy(socketPolicy)
        }

        mockWebServer.enqueue(mockResponse)
    }
}