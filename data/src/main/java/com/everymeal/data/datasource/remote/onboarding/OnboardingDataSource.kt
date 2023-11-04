package com.everymeal.data.datasource.remote.onboarding

import com.everymeal.data.model.onboarding.GetUniversityData

interface OnboardingDataSource {
    suspend fun getUniversity(): Result<GetUniversityData>
}