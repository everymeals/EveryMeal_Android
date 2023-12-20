package com.everymeal.data.repository.auth

import com.everymeal.data.datasource.auth.AuthRemoteDataSource
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.repository.auth.AuthRepository
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun postEmail(email: Email): Result<String> {
        return authRemoteDataSource.postEmail(email)
    }
}
