package com.everymeal.domain.repository.auth

import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken

interface AuthRepository {
    suspend fun postEmail(email: Email): Result<EmailAuthToken>

}
