package com.colddelight.data.repositoryImpl

import android.util.Log
import com.colddelight.data.local.Preferences
import com.colddelight.data.remote.dataSource.GithubDataSource
import com.colddelight.data.remote.dto.toDomainGithubUser
import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val pref: Preferences,
    private val api: GithubDataSource,
):GithubRepository{
    override fun saveToken(token: String) {
        pref.setGithubToken(token)
    }

    override suspend fun getUserInfo(): DomainGithubUser {
        return api.getGithubUser().toDomainGithubUser()
    }
}