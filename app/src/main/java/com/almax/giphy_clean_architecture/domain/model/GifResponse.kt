package com.almax.giphy_clean_architecture.domain.model

data class GifResponse(
    val meta: Meta,
    val data: List<GifData> = arrayListOf(),
    val pagination: Pagination
)
