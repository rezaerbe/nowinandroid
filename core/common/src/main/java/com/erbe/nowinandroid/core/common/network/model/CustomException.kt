package com.erbe.nowinandroid.core.common.network.model

data class CustomException(
    val code: Int,
    val messages: String
) : Exception()