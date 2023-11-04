package com.everymeal.domain.usecase.onboarding

import com.everymeal.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

class GetUniversityUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke() = onboardingRepository.getUniversity()
}