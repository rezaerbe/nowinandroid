package com.erbe.nowinandroid.data.article.remote.datasource

import com.erbe.nowinandroid.core.common.dispatcher.AppDispatcher
import com.erbe.nowinandroid.core.common.dispatcher.Dispatcher
import com.erbe.nowinandroid.core.common.extension.serialize
import com.erbe.nowinandroid.core.common.network.model.CustomException
import com.erbe.nowinandroid.core.common.network.model.ErrorResponse
import com.erbe.nowinandroid.data.article.remote.model.ArticleResponse
import com.erbe.nowinandroid.data.article.remote.service.ArticleService
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticleRemoteDataSourceImpl @Inject constructor(
    private val moshi: Moshi,
    private val articleService: ArticleService,
    @Dispatcher(AppDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : ArticleRemoteDataSource {

    override suspend fun getArticles(): List<ArticleResponse> =
        withContext(ioDispatcher) {
            val response = articleService.getArticles()
            // isSuccessful -> 200..299
            if (response.isSuccessful) {
                response.body()?.data?.items ?: emptyList()
            } else {
                val errorResponse = response.errorBody()?.string()
                try {
                    val errorModel =
                        errorResponse?.let { error -> serialize<ErrorResponse>(moshi, error) }!!
                    throw CustomException(errorModel.code, errorModel.message)
                } catch (error: Throwable) {
                    throw CustomException(response.code(), response.message())
                }
            }
        }
}