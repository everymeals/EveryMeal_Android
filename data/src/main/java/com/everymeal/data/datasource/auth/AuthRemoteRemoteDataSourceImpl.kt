package com.everymeal.data.datasource.auth

import com.everymeal.data.model.auth.EmailResponse
import com.everymeal.data.model.auth.toEmail
import com.everymeal.data.model.auth.toEmailAuthToken
import com.everymeal.data.model.auth.toEmailRequest
import com.everymeal.data.model.unwrapData
import com.everymeal.data.service.auth.AuthApi
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import javax.inject.Inject

class AuthRemoteRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRemoteDataSource {

    override suspend fun postEmail(email: Email): Result<EmailAuthToken> = runCatching {
        authApi.postEmail(email.toEmailRequest()).data.toEmailAuthToken()
    }
}
