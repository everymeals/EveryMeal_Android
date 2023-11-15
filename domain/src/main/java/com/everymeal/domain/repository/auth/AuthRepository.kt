package com.everymeal.domain.repository.auth

import com.everymeal.domain.model.auth.Email

interface AuthRepository {
    suspend fun postEmail(email: Email): Result<String>

}
