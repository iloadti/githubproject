package com.iloadti.testegithub.presentation.state

import com.iloadti.testegithub.domain.entities.RepoGitHubEntity

internal sealed class ListRepoState {
    data class SuccessRepoList(val items: List<RepoGitHubEntity>, val lastPage: Boolean) : ListRepoState()
}