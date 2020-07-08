package com.iloadti.testegithub.domain.usecase

import com.iloadti.testegithub.R
import com.iloadti.testegithub.TestSchedulerProvider
import com.iloadti.testegithub.data.model.PullRequestModelResponse
import com.iloadti.testegithub.domain.entities.PullRequestsEntity
import com.iloadti.testegithub.domain.repository.GitHubRepository
import com.iloadti.testegithub.mockedPullRequestModelResponse
import com.iloadti.testegithub.mockedPullRequestsEntity
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException
import java.util.concurrent.CompletableFuture

internal class GetPullRequestsUseCaseTest {

    private lateinit var repository: GitHubRepository
    private lateinit var useCase: GetPullRequestsUseCase

    private val owner = "golang"
    private val repo = "go"

    @Before
    fun setupTest() {
        repository = mock()
        useCase = GetPullRequestsUseCase(TestSchedulerProvider(), repository)
    }

    @Test
    fun `Assert execute success`() {
        //Given
        val expected: List<PullRequestsEntity> =
            arrayListOf(mockedPullRequestsEntity, mockedPullRequestsEntity)
        val response: List<PullRequestModelResponse> = arrayListOf(
            mockedPullRequestModelResponse,
            mockedPullRequestModelResponse
        )
        whenever(repository.fetchPullRequests(any(), any())).doReturn(Single.just(response))
        val future = CompletableFuture<List<PullRequestsEntity>>()

        //When
        useCase.execute(owner, repo, {
            future.complete(it)
        }, { Assert.fail() })

        // Then:
        Assert.assertEquals(expected, future.get())
        verify(repository).fetchPullRequests(owner, repo)
    }

    @Test
    fun `Assert execute error`() {
        //Given
        whenever(repository.fetchPullRequests(any(), any()))
            .doReturn(Single.error(SocketTimeoutException("Error in request")))
        val future = CompletableFuture<Int>()

        //When
        useCase.execute(owner, repo, { Assert.fail() }, {
            future.complete(it)
        })

        // Then:
        Assert.assertEquals(R.string.error_connection, future.get())
        verify(repository).fetchPullRequests(owner, repo)
    }
}