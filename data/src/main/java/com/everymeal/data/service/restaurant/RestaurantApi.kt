package com.everymeal.data.service.restaurant

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.restaruant.GetUnivRestaurantResponse
import com.everymeal.data.model.restaruant.RestaurantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantApi {

    @GET("/api/v1/stores/campus/{campusIdx}")
    suspend fun getUnivRestaurant(
        @Path("campusIdx") campusIdx: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String,
        @Query("group") group: String? = null,
        @Query("grade") grade: String? = null,
    ): BaseResponse<GetUnivRestaurantResponse>

    @GET("/api/v1/stores/{index}")
    suspend fun getRestaurantDetail(
        @Path("index") index: Int
    ): BaseResponse<RestaurantResponse>
}