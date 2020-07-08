package com.iloadti.testegithub.domain.repositories

import com.iloadti.testegithub.domain.entities.PurchaseListResponse
import com.iloadti.testegithub.network.functions.FailureError
import com.iloadti.testegithub.network.functions.ResultApi

interface SignRepository {

    suspend fun fetchPurchaseList() : ResultApi<List<PurchaseListResponse>, FailureError>

}