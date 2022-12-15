package com.colddelight.domain.model

data class DomainUserInfo(
    val user:DomainGithubUser?,
    val repo:List<DomainRepo>?
)