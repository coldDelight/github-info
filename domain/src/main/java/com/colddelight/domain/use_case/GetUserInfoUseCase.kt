package com.colddelight.domain.use_case

import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.repository.GithubRepository

class GetUserInfoUseCase (private val repository: GithubRepository){
    suspend operator fun invoke(): DomainGithubUser {
        return repository.getUserInfo()
    }
}