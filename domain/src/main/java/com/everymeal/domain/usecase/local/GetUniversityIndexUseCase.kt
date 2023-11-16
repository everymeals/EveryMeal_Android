package com.everymeal.domain.usecase.local

import com.everymeal.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversityIndexUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke() : Flow<String> {
        return localRepository.getUniversityIndex()
    }
}