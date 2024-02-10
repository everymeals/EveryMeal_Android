package com.everymeal.data.datasource.auth

import com.everymeal.data.model.auth.SignUpRequest
import com.everymeal.data.model.auth.SignUpResponse
import com.everymeal.data.model.auth.toEmailAuthToken
import com.everymeal.data.model.auth.toEmailRequest
import com.everymeal.data.service.auth.UsersApi
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import javax.inject.Inject

class UsersRemoteDataSourceImpl @Inject constructor(
    private val usersApi: UsersApi,
) : UserRemoteDataSource {
    override suspend fun signUp(signUpRequest: SignUpRequest): Result<SignUpResponse> =
        runCatching {
            usersApi.postSignup(signUpRequest).data
        }

    override suspend fun postEmail(email: Email): Result<EmailAuthToken> = runCatching {
        usersApi.postEmail(email.toEmailRequest()).data.toEmailAuthToken()
    }

    override suspend fun verifyToken(emailAuthToken: String, emailAuthValue: String) = runCatching {
        usersApi.verifyToken(emailAuthToken, emailAuthValue).data
    }
}
