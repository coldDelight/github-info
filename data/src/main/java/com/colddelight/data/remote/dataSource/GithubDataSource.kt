package com.colddelight.data.remote.dataSource

import com.colddelight.data.remote.dto.GithubUserDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface GithubDataSource {

    @Headers("Accept: application/json")
    @GET("user")
    suspend fun getGithubUser():GithubUserDto
}