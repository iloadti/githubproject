package com.iloadti.testegithub.presentation.state

import com.iloadti.testegithub.domain.entities.PullRequestsEntity

internal sealed class PullRequestsState {
    data class SuccessPullRequests(val items: List<PullRequestsEntity>) : PullRequestsState()
    object EmptyPullRequests : PullRequestsState()
}