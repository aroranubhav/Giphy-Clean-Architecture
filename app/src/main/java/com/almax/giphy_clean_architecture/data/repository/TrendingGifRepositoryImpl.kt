package com.almax.giphy_clean_architecture.data.repository

import com.almax.giphy_clean_architecture.data.remote.NetworkService
import com.almax.giphy_clean_architecture.domain.model.GifData
import com.almax.giphy_clean_architecture.domain.repository.TrendingGifRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrendingGifRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : TrendingGifRepository {

    override fun getTrendingGifs(): Flow<List<GifData>> {
        return flow {
            emit(networkService.getTrendingGifs())
        }.map {
            it.data
        }
    }
}