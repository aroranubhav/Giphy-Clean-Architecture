package com.almax.giphy_clean_architecture.data.remote

import com.almax.giphy_clean_architecture.domain.model.GifResponse
import com.almax.giphy_clean_architecture.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("offset") offset: Int = 0
    ): GifResponse
}