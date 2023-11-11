package com.everymeal.domain.usecase.local

import com.everymeal.domain.repository.local.LocalRepository
import com.everymeal.domain.repository.onboarding.OnboardingRepository
import javax.inject.Inject

class SaveUniversityUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(
        index : Int,
        univName : String
    ) {
        localRepository.saveUniversity(index, univName)
    }
}