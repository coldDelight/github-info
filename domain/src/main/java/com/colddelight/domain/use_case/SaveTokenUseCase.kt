package com.colddelight.domain.use_case

import com.colddelight.domain.repository.GithubRepository

class SaveTokenUseCase(private val repository: GithubRepository){
    // 뭘까
    operator fun invoke(token: String) = repository.saveToken(token)

}