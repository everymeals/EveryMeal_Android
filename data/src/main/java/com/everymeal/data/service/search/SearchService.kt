package com.everymeal.data.service.search

import com.everymeal.data.model.restaruant.SearchRestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    // TODO 임시 캠퍼스 ID
    @GET("/api/v1/stores/{0}/{keyword}")
    suspend fun search(
        @Path("keyword") keyword: String,
    ): SearchRestaurantResponse
}
