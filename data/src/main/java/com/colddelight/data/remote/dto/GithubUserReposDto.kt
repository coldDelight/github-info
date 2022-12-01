package com.colddelight.data.remote.dto

import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.model.DomainRepo

data class GithubUserReposDto(
    val id: Int,
    val name: String,
    val isPrivate: Boolean,
    val language: String
)

fun GithubUserReposDto.toDomainRepo(): DomainRepo {
    return DomainRepo(
        id = id,
        name = name,
        isPrivate = isPrivate,
        language = language,
    )
}