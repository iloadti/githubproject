package com.iloadti.testegithub.di

import com.iloadti.testegithub.data.remote.GitHubService
import com.iloadti.testegithub.domain.SchedulerProvider
import com.iloadti.testegithub.domain.repository.GitHubRepository
import com.iloadti.testegithub.presentation.fragment.ListRepoFragment
import com.iloadti.testegithub.presentation.fragment.PullRequestsFragment
import com.google.gson.Gson
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class GitHubModuleTest : AutoCloseKoinTest() {

    @Before
    fun setupTest(){
        startKoin { modules(fragmentModules, apiModuleService, moduleGitHubRepository, schedulerProviderModule) }
    }

    @Test
    fun `Assert if SchedulerProvider is provide by module`(){
        val schedulerProvider by inject<SchedulerProvider>()
        assertNotNull(schedulerProvider)
    }

    @Test
    fun `Assert if Gson is provide by module`(){
        val gson by inject<Gson>()
        assertNotNull(gson)
    }

    @Test
    fun `Assert if remote is provide by module`(){
        val remote by inject<GitHubService>()
        assertNotNull(remote)
    }

    @Test
    fun `Assert if repository is provide by module`(){
        val repository by inject<GitHubRepository>()
        assertNotNull(repository)
    }

    @Test
    fun `Assert if ListRepoFragment is provide by module`(){
        val fragment by inject<ListRepoFragment>()
        assertNotNull(fragment)
    }

    @Test
    fun `Assert if PullRequestsFragment is provide by module`(){
        val fragment by inject<PullRequestsFragment>()
        assertNotNull(fragment)
    }
}