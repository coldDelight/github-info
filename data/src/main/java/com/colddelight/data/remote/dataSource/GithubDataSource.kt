package com.colddelight.data.remote.dataSource

import com.colddelight.data.remote.dto.GithubUserDto
import com.colddelight.data.remote.dto.GithubUserReposDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubDataSource {

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getGithubUser():GithubUserDto

    @Headers("Accept: application/json")
    @GET("user/repos")
    suspend fun getGithubRepos():List<GithubUserReposDto>
}