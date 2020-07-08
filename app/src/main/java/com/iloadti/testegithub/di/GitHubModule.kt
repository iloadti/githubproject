package com.iloadti.testegithub.di

import com.iloadti.testegithub.commons.SchedulerProviderImp
import com.google.gson.GsonBuilder
import com.iloadti.testegithub.BuildConfig
import com.iloadti.testegithub.data.remote.GitHubService
import com.iloadti.testegithub.data.repository.GitHubRepositoryImp
import com.iloadti.testegithub.domain.SchedulerProvider
import com.iloadti.testegithub.domain.repository.GitHubRepository
import com.iloadti.testegithub.presentation.fragment.ListRepoFragment
import com.iloadti.testegithub.presentation.fragment.PullRequestsFragment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal val schedulerProviderModule = module {
    factory<SchedulerProvider> { SchedulerProviderImp() }
}

internal val apiModuleService = module {
    single { GsonBuilder().setLenient().create() }
    single<GitHubService> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC
            else HttpLoggingInterceptor.Level.NONE

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(GitHubService::class.java)
    }
}

internal val moduleGitHubRepository = module {
    factory<GitHubRepository> { GitHubRepositoryImp(get()) }
}

internal val fragmentModules = module {
    fragment { ListRepoFragment() }
    fragment { PullRequestsFragment() }
}