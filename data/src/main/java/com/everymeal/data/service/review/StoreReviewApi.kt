package com.everymeal.data.service.review

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.StoreReviewRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StoreReviewApi {
//    @GET("/api/v1/stores/{index}/reviews")
//    suspend fun getStoreReviewsWithId(
//        @Query("index") index: Int,
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int,
//    ): BaseResponse<ReviewListResponse>

    @GET("/api/v1/stores/reviews")
    suspend fun getStoresReviews(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("order") order: String?,
        @Query("group") group: String?,
        @Query("grade") grade: Int?,
    ): BaseResponse<ReviewListResponse>

    @POST("/api/v1/reviews/store")
    suspend fun postStoreReview(
        @Body storeReviewRequest: StoreReviewRequest,
    ): BaseResponse<Boolean>
}
