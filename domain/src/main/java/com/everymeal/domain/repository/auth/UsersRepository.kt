package com.everymeal.domain.repository.auth

import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import com.everymeal.domain.model.auth.User
import com.everymeal.domain.model.auth.UserSignUp

interface UsersRepository {
    suspend fun signUp(signUp: UserSignUp): Result<User>
    suspend fun postEmail(email: Email): Result<EmailAuthToken>
    suspend fun verifyToken(emailAuthToken: String, emailAuthValue: String): Result<Boolean>

}
