package com.everymeal.domain.repository.splash

import kotlinx.coroutines.flow.Flow


interface SplashRepository {
    suspend fun getUniversityIndex(): Flow<String>
}