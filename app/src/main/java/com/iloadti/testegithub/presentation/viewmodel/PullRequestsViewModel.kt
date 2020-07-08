package com.iloadti.testegithub.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iloadti.testegithub.domain.entities.RepoGitHubEntity
import com.iloadti.testegithub.domain.usecase.GetPullRequestsUseCase
import com.iloadti.testegithub.presentation.state.ErrorState
import com.iloadti.testegithub.presentation.state.LoadState
import com.iloadti.testegithub.presentation.state.PullRequestsState

internal class PullRequestsViewModel(private val useCase: GetPullRequestsUseCase) : ViewModel() {

    private val _pullRequestState by lazy { MutableLiveData<PullRequestsState>() }
    private val _loadState by lazy { MutableLiveData<LoadState>() }
    private val _errorState by lazy { MutableLiveData<ErrorState>() }

    val state: LiveData<PullRequestsState>
        get() = _pullRequestState

    val stateLoad: LiveData<LoadState>
        get() = _loadState

    val stateError: LiveData<ErrorState>
        get() = _errorState

    override fun onCleared() {
        useCase.dispose()
        super.onCleared()
    }

    fun getPullRequests(repo: RepoGitHubEntity?) {
        _loadState.value = LoadState.ShowLoading
        useCase.execute(repo?.username, repo?.nameRepo, {
            _pullRequestState.value = if (it.isNullOrEmpty()) PullRequestsState.EmptyPullRequests
            else PullRequestsState.SuccessPullRequests(it)
            _loadState.value = LoadState.HideLoading
        }, {
            _errorState.value = ErrorState.ShowError(it)
            _loadState.value = LoadState.HideLoading
        })
    }
}