package com.everymeal.data.service.auth

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.auth.EmailRequest
import com.everymeal.data.model.auth.EmailResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("/api/v1/users/email")
    suspend fun postEmail(
        @Body emailRequest: EmailRequest
    ): BaseResponse<EmailResponse>

    @GET("/api/v1/users/email/verify")
    suspend fun verifyToken(
        @Query("emailAuthToken") emailAuthToken: String,
        @Query("emailAuthValue") emailAuthValue: String
    ): BaseResponse<Boolean>
}
