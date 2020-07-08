package com.iloadti.testegithub.domain.usecase

import com.iloadti.testegithub.commons.BaseUseCase
import com.iloadti.testegithub.data.model.SearchResponseModel
import com.iloadti.testegithub.domain.SchedulerProvider
import com.iloadti.testegithub.domain.entities.RepoGitHubEntity
import com.iloadti.testegithub.domain.entities.RepositoriesEntity
import com.iloadti.testegithub.domain.repository.GitHubRepository
import com.iloadti.testegithub.utils.STRING_DEFAULT

internal class GetListRepoUseCase(scheduler: SchedulerProvider, private val repository: GitHubRepository) :
    BaseUseCase<RepositoriesEntity>(scheduler) {

    fun execute(
        page: Long,
        onSuccess: (RepositoriesEntity) -> Unit,
        onError: (Int) -> Unit
    ) {
        repository
            .fetchRepo(page)
            ?.map { transformRepositoriesEntity(it) }
            ?.executeUseCase(onSuccess, onError)
    }

    private fun transformRepositoriesEntity(it: SearchResponseModel?) = RepositoriesEntity(
        totalCount = it?.totalCount ?: 0,
        items = it?.items?.map { repo ->
            RepoGitHubEntity(
                nameRepo = repo.nameRepo ?: STRING_DEFAULT,
                descriptionRepo = repo.description ?: STRING_DEFAULT,
                username = repo.owner?.username ?: STRING_DEFAULT,
                avatar = repo.owner?.avatarUrl ?: STRING_DEFAULT,
                forksCount = repo.forksCount?.toString() ?: STRING_DEFAULT,
                stargazersCount = repo.stargazersCount?.toString() ?: STRING_DEFAULT
            )
        } ?: arrayListOf()
    )
}