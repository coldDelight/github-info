package com.colddelight.domain.repository

import com.colddelight.domain.model.DomainGithubUser

interface GithubRepository {
    fun saveToken(token: String)

    suspend fun getUserInfo(): DomainGithubUser

}