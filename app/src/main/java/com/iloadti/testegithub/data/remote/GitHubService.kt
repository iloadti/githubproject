package com.iloadti.testegithub.data.remote

import com.iloadti.testegithub.data.model.PullRequestModelResponse
import com.iloadti.testegithub.data.model.SearchResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories?q=language:java&sort=stars&order=desc")
    fun getSearch(@Query("page") page: Long): Observable<SearchResponseModel>

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("repos/{owner}/{repo}/pulls")
    fun getPulls(@Path("owner") owner: String, @Path("repo") repository: String): Observable<List<PullRequestModelResponse>>
}