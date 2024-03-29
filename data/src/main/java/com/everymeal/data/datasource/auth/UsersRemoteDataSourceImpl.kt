package com.everymeal.data.datasource.auth

import com.everymeal.data.model.auth.SignUpRequest
import com.everymeal.data.model.auth.toEmailAuthToken
import com.everymeal.data.model.auth.toEmailRequest
import com.everymeal.data.service.auth.UsersApi
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import com.everymeal.domain.model.auth.User
import javax.inject.Inject

class UsersRemoteDataSourceImpl @Inject constructor(
    private val usersApi: UsersApi,
) : UserRemoteDataSource {
    override suspend fun signUp(signUpRequest: SignUpRequest): Result<User> {
        val response = usersApi.postSignup(signUpRequest)
        return if (response.isSuccessful) {
            val header = response.headers()
            val cookie = header["Set-Cookie"]?.split(";")?.get(0)
            val refreshToken = cookie?.replace("refreshToken=", "")
            Result.success(
                User(
                    accessToken = response.body()?.data?.accessToken.orEmpty(),
                    refreshToken = refreshToken.orEmpty(),
                    nickname = response.body()?.data?.nickname.orEmpty(),
                    profileImg = response.body()?.data?.profileImg.orEmpty()
                )
            )
        } else {
            Result.failure(Throwable(response.errorBody()?.string()))
        }
    }


    override suspend fun postEmail(email: Email): Result<EmailAuthToken> = runCatching {
        usersApi.postEmail(email.toEmailRequest()).data.toEmailAuthToken()
    }

    override suspend fun verifyToken(emailAuthToken: String, emailAuthValue: String) = runCatching {
        usersApi.verifyToken(emailAuthToken, emailAuthValue).data
    }
}
