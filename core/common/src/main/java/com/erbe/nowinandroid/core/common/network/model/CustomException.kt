package com.erbe.nowinandroid.core.common.network.model

data class ApiException(val code: Int, override val message: String) : Exception(message)
data class NetworkException(override val message: String) : Exception(message)
data class ParsingException(override val message: String) : Exception(message)
data class EmptyException(override val message: String) : Exception(message)