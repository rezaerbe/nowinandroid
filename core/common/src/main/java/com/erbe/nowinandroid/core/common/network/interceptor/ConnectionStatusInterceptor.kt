package com.erbe.nowinandroid.core.common.network.interceptor

import com.erbe.nowinandroid.core.common.network.connection.ConnectionManager
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionStatusInterceptor @Inject constructor(
    private val connectionManager: ConnectionManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw IOException("Connection error")
        }
    }
}