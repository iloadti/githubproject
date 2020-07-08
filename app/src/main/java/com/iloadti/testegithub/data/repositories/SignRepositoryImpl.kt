package com.iloadti.testegithub.data.repositories

import com.iloadti.testegithub.data.core.RequestApi
import com.iloadti.testegithub.data.service.Api
import com.iloadti.testegithub.domain.entities.PurchaseListResponse
import com.iloadti.testegithub.network.functions.FailureError
import com.iloadti.testegithub.network.functions.ResultApi
import com.iloadti.testegithub.domain.repositories.SignRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class SignRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val api: Api,
    private val requestApi: RequestApi
) : SignRepository {

    override suspend fun fetchPurchaseList(): ResultApi<List<PurchaseListResponse>, FailureError> {
        return requestApi.onRequest(dispatcher) { api.fetchPurchaseList() }
    }
}