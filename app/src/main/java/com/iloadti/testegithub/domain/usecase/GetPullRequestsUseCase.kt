package com.iloadti.testegithub.domain.usecase

import com.iloadti.testegithub.data.model.PullRequestModelResponse
import com.iloadti.testegithub.commons.BaseUseCase
import com.iloadti.testegithub.domain.SchedulerProvider
import com.iloadti.testegithub.domain.entities.PullRequestsEntity
import com.iloadti.testegithub.domain.repository.GitHubRepository
import com.iloadti.testegithub.extension.formatToDateAndHour
import com.iloadti.testegithub.utils.STRING_DEFAULT

internal class GetPullRequestsUseCase(scheduler: SchedulerProvider, private val repository: GitHubRepository) :
    BaseUseCase<List<PullRequestsEntity>>(scheduler) {

    fun execute(
        owner: String?, repoName: String?,
        onSuccess: (List<PullRequestsEntity>) -> Unit,
        onError: (Int) -> Unit
    ) {
        repository
            .fetchPullRequests(owner ?: STRING_DEFAULT, repoName ?: STRING_DEFAULT)
            ?.map { transformPullRequestModelEntity(it) }
            ?.executeUseCase(onSuccess, onError)
    }

    private fun transformPullRequestModelEntity(response: List<PullRequestModelResponse>?) =
        response?.map {
            PullRequestsEntity(
                title = it.title ?: STRING_DEFAULT,
                description = it.body ?: STRING_DEFAULT,
                username = it.user?.username ?: STRING_DEFAULT,
                avatarUrl = it.user?.avatarUrl ?: STRING_DEFAULT,
                createdDate = it.createdDate?.formatToDateAndHour() ?: STRING_DEFAULT
            )
        } ?: run { arrayListOf<PullRequestsEntity>() }
}