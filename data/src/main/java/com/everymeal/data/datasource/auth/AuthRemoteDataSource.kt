package com.everymeal.data.datasource.auth

import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken

interface AuthRemoteDataSource {
    suspend fun postEmail(email: Email): Result<EmailAuthToken>
    suspend fun verifyToken(emailAuthToken: String, emailAuthValue: String): Result<Boolean>
}
