package com.everymeal.data.datasource.remote.onboarding

import com.everymeal.data.model.onboarding.GetUniversityData
import com.everymeal.data.service.onboarding.OnboardingApi
import javax.inject.Inject

class OnboardingDataSourceImpl @Inject constructor(
    private val onboardingApi: OnboardingApi
) : OnboardingDataSource {

    override suspend fun getUniversity(): Result<GetUniversityData> {
        return runCatching { onboardingApi.getUniversity() }
    }
}