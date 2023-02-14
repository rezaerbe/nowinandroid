package com.erbe.nowinandroid.core.common.network.util

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, Call<*>>? {
        if (getRawType(returnType) != Call::class.java ||
            returnType !is ParameterizedType ||
            returnType.actualTypeArguments.size != 1
        ) {
            return null
        }

        val call = retrofit.nextCallAdapter(this, returnType, annotations)
        @Suppress("UNCHECKED_CAST")
        return NetworkCallAdapter(callAdapter = call as CallAdapter<Any, Call<*>>)
    }

    companion object {
        fun create() = NetworkCallAdapterFactory()
    }
}