package com.colddelight.domain.repository

import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.model.DomainRepo

interface GithubRepository {
    fun saveToken(token: String)

    suspend fun getUserInfo(): DomainGithubUser

    suspend fun getUserRepos(): List<DomainRepo>

}