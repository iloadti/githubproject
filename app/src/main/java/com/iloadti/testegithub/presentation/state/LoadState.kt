package com.iloadti.testegithub.presentation.state

internal sealed class LoadState {
    object ShowLoading: LoadState()
    object HideLoading: LoadState()
}