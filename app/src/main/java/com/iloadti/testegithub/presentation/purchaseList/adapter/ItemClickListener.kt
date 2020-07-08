package com.iloadti.testegithub.presentation.purchaseList.adapter

import com.iloadti.testegithub.domain.entities.PurchaseListResponse

interface ItemClickListener {
    fun onItemClickListener(purchaseListResponse: PurchaseListResponse)
}