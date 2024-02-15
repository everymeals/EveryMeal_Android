package com.everymeal.data.service.search

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.restaruant.SearchRestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {
    // TODO 임시 캠퍼스 ID
    @GET("/api/v1/stores/{campusIdx}/{keyword}")
    suspend fun search(
        @Path("campusIdx") campusIdx: Int,
        @Path("keyword") keyword: String,
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?,
    ): SearchRestaurantResponse
}
