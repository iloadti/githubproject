package com.iloadti.testegithub.domain.usecase

import com.iloadti.testegithub.domain.entities.PurchaseListResponse
import com.iloadti.testegithub.domain.repositories.SignRepository
import com.iloadti.testegithub.network.functions.FailureError
import com.iloadti.testegithub.network.functions.ResultApi

class HomeUseCase(private val homeRepository: SignRepository) {

    suspend fun fetchPublicKey(): ResultApi<List<PurchaseListResponse>, FailureError> =
        homeRepository.fetchPurchaseList()
}