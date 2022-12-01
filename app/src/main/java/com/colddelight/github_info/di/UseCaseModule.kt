package com.colddelight.github_info.di

import com.colddelight.domain.repository.GithubRepository
import com.colddelight.domain.use_case.GetUserInfoUseCase
import com.colddelight.domain.use_case.GetUserReposUseCase
import com.colddelight.domain.use_case.SaveTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideSaveTokenUseCase(repository: GithubRepository): SaveTokenUseCase {
        return SaveTokenUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserInfoUseCase(repository: GithubRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetUserReposUseCase(repository: GithubRepository): GetUserReposUseCase {
        return GetUserReposUseCase(repository)
    }
}