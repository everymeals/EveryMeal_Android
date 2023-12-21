package com.everymeal.data.repository

import com.everymeal.data.datasource.auth.AuthRemoteDataSource
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import com.everymeal.domain.repository.auth.AuthRepository
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun postEmail(email: Email): Result<EmailAuthToken> {
        return authRemoteDataSource.postEmail(email)
    }

    override suspend fun verifyToken(
        emailAuthToken: String,
        emailAuthValue: String
    ): Result<Boolean> {
        return authRemoteDataSource.verifyToken(emailAuthToken, emailAuthValue)
    }
}
