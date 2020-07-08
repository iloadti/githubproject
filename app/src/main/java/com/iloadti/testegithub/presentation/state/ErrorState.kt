package com.iloadti.testegithub.presentation.state

import androidx.annotation.StringRes

internal sealed class ErrorState {
    data class ShowError(@StringRes val idRes: Int) : ErrorState()
}