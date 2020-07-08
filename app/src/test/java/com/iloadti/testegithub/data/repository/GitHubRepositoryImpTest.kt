package com.iloadti.testegithub.data.repository

import com.iloadti.testegithub.data.model.PullRequestModelResponse
import com.iloadti.testegithub.data.model.SearchResponseModel
import com.iloadti.testegithub.data.remote.GitHubService
import com.iloadti.testegithub.domain.repository.GitHubRepository
import com.iloadti.testegithub.mockedPullRequestModelResponse
import com.iloadti.testegithub.mockedSearchResponseModel
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

internal class GitHubRepositoryImpTest {

    private lateinit var repository: GitHubRepository
    private lateinit var service: GitHubService

    @Before
    fun init() {
        service = mock()
        repository = GitHubRepositoryImp(service)
    }

    @Test
    fun `Assert fetchRepo`() {
        // given:
        val page = 1L
        val expected = mockedSearchResponseModel

        whenever(service.getSearch(page)).doReturn(Observable.just(expected))
        val testObserver = TestObserver<SearchResponseModel>()

        // when:
        val repositoryRequest = repository.fetchRepo(page)
        repositoryRequest?.subscribeWith(testObserver)

        // then:
        testObserver.assertResult(expected)
        verify(service).getSearch(page)
        assertNotNull(repositoryRequest)

        testObserver.dispose()
    }

    @Test
    fun `Assert fetchPullRequests`() {
        // given:
        val owner = "golang"
        val repo = "go"
        val expected: List<PullRequestModelResponse> = arrayListOf(mockedPullRequestModelResponse, mockedPullRequestModelResponse)

        whenever(service.getPulls(owner, repo)).doReturn(Observable.just(expected))
        val testObserver = TestObserver<List<PullRequestModelResponse>>()

        // when:
        val repositoryRequest = repository.fetchPullRequests(owner, repo)
        repositoryRequest?.subscribeWith(testObserver)

        // then:
        testObserver.assertResult(expected)
        verify(service).getPulls(owner, repo)
        assertNotNull(repositoryRequest)

        testObserver.dispose()
    }


}