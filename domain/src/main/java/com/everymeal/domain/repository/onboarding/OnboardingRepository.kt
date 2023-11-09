package com.everymeal.domain.repository.onboarding

import com.everymeal.domain.model.onboarding.GetUniversityEntity

interface OnboardingRepository {
    suspend fun getUniversity(): Result<GetUniversityEntity>
}