package com.almax.giphy_clean_architecture.domain.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int = 0,
    val count: Int = 0,
    val offset: Int = 0
)
