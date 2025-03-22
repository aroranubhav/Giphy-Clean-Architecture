package com.almax.giphy_clean_architecture.domain.usecase

import com.almax.giphy_clean_architecture.domain.model.GifData
import com.almax.giphy_clean_architecture.domain.repository.TrendingGifRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingGifsUseCase @Inject constructor(
    private val repository: TrendingGifRepository
) {

    operator fun invoke(): Flow<List<GifData>> {
        return repository.getTrendingGifs()
    }
}