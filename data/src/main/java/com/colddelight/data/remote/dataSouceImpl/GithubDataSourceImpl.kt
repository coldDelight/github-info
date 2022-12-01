package com.colddelight.data.remote.dataSouceImpl

import com.colddelight.data.remote.dataSource.GithubDataSource
import com.colddelight.data.remote.dto.GithubUserDto
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): GithubDataSource {
    override suspend fun getGithubUser(): GithubUserDto {
        return retrofit.create(GithubDataSource::class.java).getGithubUser()
    }

}