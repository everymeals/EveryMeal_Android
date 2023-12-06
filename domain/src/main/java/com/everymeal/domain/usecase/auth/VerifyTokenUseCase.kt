package com.everymeal.domain.usecase.auth

import com.everymeal.domain.repository.auth.AuthRepository
import javax.inject.Inject

class VerifyTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        emailAuthToken: String,
        emailAuthValue: String
    ): Result<Boolean> {
        return authRepository.verifyToken(emailAuthToken, emailAuthValue)
    }
}
