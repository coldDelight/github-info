package com.colddelight.domain.model

data class DomainGithubUser(
    val repos: Int,
    val gists: Int,
    val name: String,
    val image: String,
    val followers: Int,
    val following: Int,
)