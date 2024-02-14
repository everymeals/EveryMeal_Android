package com.everymeal.data.datasource.auth

import com.everymeal.data.model.auth.SignUpRequest
import com.everymeal.data.model.auth.SignUpResponse
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import com.everymeal.domain.model.auth.User

interface UserRemoteDataSource {
    suspend fun signUp(signUpRequest: SignUpRequest): Result<User>
    suspend fun postEmail(email: Email): Result<EmailAuthToken>
    suspend fun verifyToken(emailAuthToken: String, emailAuthValue: String): Result<Boolean>
}
