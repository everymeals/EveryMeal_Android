package com.everymeal.data.service.review

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.review.ReviewListResponse
import com.everymeal.data.model.review.ReviewRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApi {
    @PUT("/api/v1/reviews/{reviewIdx}")
    suspend fun putReview(
        @Path("reviewIdx") reviewIdx: Int,
        @Body reviewRequest: ReviewRequest
    ): BaseResponse<Boolean>

    @DELETE("/api/v1/reviews/{reviewIdx}")
    suspend fun deleteReview(
        @Path("reviewIdx") reviewIdx: Int
    ): BaseResponse<Unit>

    @GET("/api/v1/reviews")
    suspend fun getReviewList(
        @Query("cursorIdx") cursorIdx: Int,
        @Query("mealIdx") mealIdx: Int,
        @Query("pageSize") pageSize: Int,
    ): BaseResponse<ReviewListResponse>

    @POST("/api/v1/reviews")
    suspend fun postReview(
        @Body reviewRequest: ReviewRequest
    ): BaseResponse<Boolean>

}
