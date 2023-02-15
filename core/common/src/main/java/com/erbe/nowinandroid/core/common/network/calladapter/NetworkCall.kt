package com.erbe.nowinandroid.core.common.network.calladapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class NetworkCall(
    private val call: Call<Any>
) : Call<Any> by call {

    override fun enqueue(callback: Callback<Any>) {
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    callback.onFailure(call, handleApiError(HttpException(response)))
                }
            }

            override fun onFailure(call: Call<Any>, error: Throwable) {
                callback.onFailure(call, handleNetworkError(error))
            }
        })
    }

    override fun clone(): Call<Any> = NetworkCall(call.clone())
}