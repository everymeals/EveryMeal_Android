package com.everymeal.data.datasource.auth

import com.everymeal.data.model.auth.toEmailAuthToken
import com.everymeal.data.model.auth.toEmailRequest
import com.everymeal.data.service.auth.UsersApi
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import javax.inject.Inject

class AuthRemoteRemoteDataSourceImpl @Inject constructor(
    private val usersApi: UsersApi,
) : AuthRemoteDataSource {

    override suspend fun postEmail(email: Email): Result<EmailAuthToken> = runCatching {
        usersApi.postEmail(email.toEmailRequest()).data.toEmailAuthToken()
    }

    override suspend fun verifyToken(emailAuthToken: String, emailAuthValue: String) = runCatching {
        usersApi.verifyToken(emailAuthToken, emailAuthValue).data
    }
}
