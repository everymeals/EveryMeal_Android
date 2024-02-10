package com.everymeal.domain.usecase.auth

import com.everymeal.domain.repository.auth.UsersRepository
import javax.inject.Inject

class VerifyTokenUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(
        emailAuthToken: String,
        emailAuthValue: String
    ): Result<Boolean> {
        return usersRepository.verifyToken(emailAuthToken, emailAuthValue)
    }
}
