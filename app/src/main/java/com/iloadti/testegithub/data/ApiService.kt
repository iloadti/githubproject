package com.iloadti.testegithub.data

import com.iloadti.testegithub.data.service.Api
import retrofit2.Retrofit

class ApiService(private val retrofit: Retrofit) : Api {

    private val api by lazy { retrofit.create(Api::class.java) }

    override suspend fun fetchPurchaseList() = api.fetchPurchaseList()

}