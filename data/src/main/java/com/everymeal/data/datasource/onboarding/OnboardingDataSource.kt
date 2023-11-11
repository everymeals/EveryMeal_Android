package com.everymeal.data.datasource.onboarding

import com.everymeal.data.model.onboarding.UniversityResponse

interface OnboardingDataSource {
    suspend fun getUniversity(): Result<List<UniversityResponse>>
}