package com.everymeal.data.repository.onboarding

import com.everymeal.data.datasource.remote.onboarding.OnboardingDataSource
import com.everymeal.data.model.onboarding.toUniversityEntity
import com.everymeal.domain.model.onboarding.GetUniversityEntity
import com.everymeal.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingDataSource: OnboardingDataSource
): OnboardingRepository {

    override suspend fun getUniversity(): Result<GetUniversityEntity> {
        return onboardingDataSource.getUniversity().map { it.toUniversityEntity() }
    }
}