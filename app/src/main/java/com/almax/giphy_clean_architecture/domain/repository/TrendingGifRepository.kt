package com.almax.giphy_clean_architecture.domain.repository

import com.almax.giphy_clean_architecture.domain.model.GifData
import kotlinx.coroutines.flow.Flow

interface TrendingGifRepository {

    fun getTrendingGifs(): Flow<List<GifData>>
}