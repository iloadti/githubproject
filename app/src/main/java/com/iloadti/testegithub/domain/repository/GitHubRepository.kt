package com.iloadti.testegithub.domain.repository

import com.iloadti.testegithub.data.model.PullRequestModelResponse
import com.iloadti.testegithub.data.model.SearchResponseModel
import io.reactivex.Single

interface GitHubRepository {

    @Throws(Exception::class)
    fun fetchRepo(page: Long): Single<SearchResponseModel>?


    @Throws(Exception::class)
    fun fetchPullRequests(owner: String, repository: String): Single<List<PullRequestModelResponse>>?
}