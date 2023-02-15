package com.erbe.nowinandroid.core.common.network.calladapter

import retrofit2.Call
import retrofit2.CallAdapter

class NetworkCallAdapter(
    private val callAdapter: CallAdapter<Any, Call<*>>
) : CallAdapter<Any, Call<*>> by callAdapter {

    override fun adapt(call: Call<Any>): Call<*> {
        return callAdapter.adapt(NetworkCall(call))
    }
}