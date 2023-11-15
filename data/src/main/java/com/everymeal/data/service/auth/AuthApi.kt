package com.everymeal.data.service.auth

import com.everymeal.data.model.BaseResponse
import com.everymeal.data.model.auth.EmailRequest
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/v1/users/email")
    suspend fun postEmail(emailRequest: EmailRequest): BaseResponse<String>
}
