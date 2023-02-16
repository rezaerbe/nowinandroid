package com.erbe.nowinandroid.core.common.network.model

data class ApiException(val code: Int, val messages: String) : Exception()
data class NetworkException(val messages: String) : Exception()
data class DataException(val messages: String) : Exception()
data class EmptyException(val messages: String) : Exception()