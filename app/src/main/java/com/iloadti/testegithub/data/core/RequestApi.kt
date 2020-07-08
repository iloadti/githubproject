package com.iloadti.testegithub.data.core

import com.iloadti.testegithub.network.functions.FailureError
import com.iloadti.testegithub.network.functions.ResultApi
import kotlinx.coroutines.CoroutineDispatcher

interface RequestApi {

    suspend fun <T>onRequest(
        dispatcher: CoroutineDispatcher,
        api: suspend () -> T
    ): ResultApi<T, FailureError>
}