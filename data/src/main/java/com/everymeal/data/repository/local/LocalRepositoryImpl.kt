package com.everymeal.data.repository.local

import com.everymeal.data.datasource.local.LocalDataSource
import com.everymeal.domain.repository.local.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : LocalRepository {

    override suspend fun saveUniversity(index: Int, univName: String) {
        localDataSource.saveUniversity(index, univName)
    }
}