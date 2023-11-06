package com.everymeal.data.datasource.onboarding

import com.everymeal.data.model.onboarding.UniversityData
import com.everymeal.data.model.unwrapData
import com.everymeal.data.service.onboarding.OnboardingApi
import javax.inject.Inject

class OnboardingDataSourceImpl @Inject constructor(
    private val onboardingApi: OnboardingApi
) : OnboardingDataSource {

    override suspend fun getUniversity(): Result<List<UniversityData>> {
        return runCatching { onboardingApi.getUniversity() }.unwrapData()
    }
}