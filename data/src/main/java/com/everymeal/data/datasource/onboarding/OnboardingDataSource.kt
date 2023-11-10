package com.everymeal.data.datasource.onboarding

import com.everymeal.data.model.onboarding.UniversityData

interface OnboardingDataSource {
    suspend fun getUniversity(): Result<List<UniversityData>>
}