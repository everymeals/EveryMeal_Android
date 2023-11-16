package com.everymeal.data.repository.splash

import com.everymeal.data.datasource.local.LocalDataSource
import com.everymeal.domain.repository.splash.SplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): SplashRepository {

    override suspend fun getUniversityIndex(): Flow<String> {
        return localDataSource.getUniversityIndex()
    }
}