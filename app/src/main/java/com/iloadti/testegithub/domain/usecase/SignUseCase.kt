package com.iloadti.testegithub.domain.usecase

import com.iloadti.testegithub.domain.entities.PurchaseListResponse
import com.iloadti.testegithub.domain.repositories.SignRepository
import com.iloadti.testegithub.network.functions.FailureError
import com.iloadti.testegithub.network.functions.ResultApi

class SignUseCase(private val signRepository: SignRepository) {

    suspend fun fetchPublicKey(): ResultApi<List<PurchaseListResponse>, FailureError> =
        signRepository.fetchPurchaseList()
}