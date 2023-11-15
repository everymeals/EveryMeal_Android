package com.everymeal.data.datasource.auth

import com.everymeal.domain.model.auth.Email

interface AuthRemoteDataSource {
    suspend fun postEmail(email: Email): Result<String>
}
