package com.colddelight.data.remote.dataSouceImpl

import com.colddelight.data.remote.dataSource.GithubDataSource
import com.colddelight.data.remote.dto.GithubUserDto
import com.colddelight.data.remote.dto.GithubUserReposDto
import com.colddelight.domain.model.DomainRepo
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class GithubDataSourceImpl @Inject constructor(
    private val retrofit: Retrofit
): GithubDataSource {
    override suspend fun getGithubUser(): GithubUserDto {
        return retrofit.create(GithubDataSource::class.java).getGithubUser()
    }

    override suspend fun getGithubRepos(): List<GithubUserReposDto> {
        return retrofit.create(GithubDataSource::class.java).getGithubRepos()
    }

}