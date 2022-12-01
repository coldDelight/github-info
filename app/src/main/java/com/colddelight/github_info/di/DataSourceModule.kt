package com.colddelight.github_info.di

import com.colddelight.data.remote.dataSouceImpl.GithubDataSourceImpl
import com.colddelight.data.remote.dataSource.GithubDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideGithubDataSource(
        retrofit: Retrofit
    ): GithubDataSource {
        return GithubDataSourceImpl(retrofit)
    }

}