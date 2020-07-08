package com.iloadti.testegithub.di

import androidx.annotation.VisibleForTesting
import com.iloadti.testegithub.domain.usecase.GetPullRequestsUseCase
import com.iloadti.testegithub.presentation.viewmodel.PullRequestsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@VisibleForTesting
internal val modulePullRequests = module {
    factory { GetPullRequestsUseCase(get(), get()) }
    viewModel { PullRequestsViewModel(get()) }
}

private val providerPullRequests by lazy { loadKoinModules(modulePullRequests) }
internal fun loadModulePullRequests() = providerPullRequests
