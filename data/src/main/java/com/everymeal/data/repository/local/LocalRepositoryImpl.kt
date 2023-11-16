package com.everymeal.data.repository.local

import com.everymeal.data.datasource.local.LocalDataSource
import com.everymeal.domain.repository.local.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {

    override suspend fun saveUniversity(index: Int, univName: String) {
        localDataSource.saveUniversity(index, univName)
    }

    override suspend fun getUniversityIndex(): Flow<String> {
        return localDataSource.getUniversityIndex()
    }
}