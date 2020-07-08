package com.iloadti.testegithub.data.repository

import com.iloadti.testegithub.data.model.PullRequestModelResponse
import com.iloadti.testegithub.data.model.SearchResponseModel
import com.iloadti.testegithub.data.remote.GitHubService
import com.iloadti.testegithub.domain.repository.GitHubRepository
import io.reactivex.Single

internal class GitHubRepositoryImp(private val service: GitHubService) : GitHubRepository {

    @Throws(Exception::class)
    override fun fetchRepo(page: Long): Single<SearchResponseModel>? =
        service.getSearch(page).firstOrError()

    @Throws(Exception::class)
    override fun fetchPullRequests(owner: String, repository: String): Single<List<PullRequestModelResponse>>? =
        service.getPulls(owner, repository).firstOrError()
}