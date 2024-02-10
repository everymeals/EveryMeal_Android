package com.everymeal.data.service.auth

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.auth.EmailRequest
import com.everymeal.data.model.auth.EmailResponse
import com.everymeal.data.model.auth.SignUpRequest
import com.everymeal.data.model.auth.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UsersApi {

    @GET("/api/v1/users/profile")
    suspend fun getProfile(): BaseResponse<Any>

    @PUT("/api/v1/users/profile")
    suspend fun putProfile(): BaseResponse<Any>

    @POST("/api/v1/users/withdrawal")
    suspend fun postWithdrawal(): BaseResponse<Any>

    @POST("/api/v1/users/signup")
    suspend fun postSignup(signUpRequest: SignUpRequest): BaseResponse<SignUpResponse>

    @POST("/api/v1/users/login")
    suspend fun postLogin(): BaseResponse<Any>

    @GET("/api/v1/users/email")
    suspend fun getEmail(
        @Query("email") email: String
    ): BaseResponse<EmailResponse>

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
