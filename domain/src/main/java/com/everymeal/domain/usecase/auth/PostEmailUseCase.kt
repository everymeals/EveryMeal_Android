package com.everymeal.domain.usecase.auth

import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.model.auth.EmailAuthToken
import com.everymeal.domain.repository.auth.AuthRepository
import javax.inject.Inject

class PostEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: Email
    ): Result<EmailAuthToken> {
        return authRepository.postEmail(email)
    }
}
