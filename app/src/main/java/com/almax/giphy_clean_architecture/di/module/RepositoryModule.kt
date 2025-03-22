package com.almax.giphy_clean_architecture.di.module

import com.almax.giphy_clean_architecture.data.remote.NetworkService
import com.almax.giphy_clean_architecture.data.repository.TrendingGifRepositoryImpl
import com.almax.giphy_clean_architecture.domain.repository.TrendingGifRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTrendingGifRepository(
        networkService: NetworkService
    ): TrendingGifRepository {
        return TrendingGifRepositoryImpl(networkService)
    }
}
