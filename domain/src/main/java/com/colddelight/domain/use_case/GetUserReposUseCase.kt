package com.colddelight.domain.use_case

import com.colddelight.domain.model.DomainRepo
import com.colddelight.domain.repository.GithubRepository

class GetUserReposUseCase (private val repository: GithubRepository){
    suspend operator fun invoke(): List<DomainRepo> {
        return repository.getUserRepos()
    }
}