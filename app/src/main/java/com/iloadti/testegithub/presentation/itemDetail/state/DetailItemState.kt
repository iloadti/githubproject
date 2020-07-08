package com.iloadti.testegithub.presentation.itemDetail.state

import com.iloadti.testegithub.domain.entities.PurchaseListResponse

sealed class DetailItemState {
    data class ShowItemDetail(val value: PurchaseListResponse) : DetailItemState()
    data class ShowError(val ex: Throwable) : DetailItemState()
}