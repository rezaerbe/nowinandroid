package com.erbe.nowinandroid.core.common.network.connection

import kotlinx.coroutines.flow.Flow

interface ConnectionManager {

    fun isConnected(): Boolean
    fun connectionStatus(): Flow<Boolean>
}