package com.everymeal.data.datasource.auth

import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken

interface AuthRemoteDataSource {
    suspend fun postEmail(email: Email): Result<EmailAuthToken>
}
