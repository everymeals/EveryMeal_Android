package com.everymeal.data.repository.auth

import com.everymeal.data.datasource.auth.UserRemoteDataSource
import com.everymeal.data.model.auth.toSignUpRequest
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import com.everymeal.domain.model.auth.User
import com.everymeal.domain.model.auth.UserSignUp
import com.everymeal.domain.repository.auth.UsersRepository
import javax.inject.Inject

class DefaultUsersRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UsersRepository {
    override suspend fun signUp(signUp: UserSignUp): Result<User> {
        return userRemoteDataSource.signUp(signUp.toSignUpRequest()).map {
            it.toUser()
        }
    }

    override suspend fun postEmail(email: Email): Result<EmailAuthToken> {
        return userRemoteDataSource.postEmail(email)
    }

    override suspend fun verifyToken(
        emailAuthToken: String,
        emailAuthValue: String,
    ): Result<Boolean> {
        return userRemoteDataSource.verifyToken(emailAuthToken, emailAuthValue)
    }
}
