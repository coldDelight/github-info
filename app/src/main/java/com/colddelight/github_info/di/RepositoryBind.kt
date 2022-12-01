package com.colddelight.github_info.di

import com.colddelight.data.repositoryImpl.GithubRepositoryImpl
import com.colddelight.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBind {
    @Binds
    abstract fun bindAuthRepository(impl: GithubRepositoryImpl): GithubRepository
}