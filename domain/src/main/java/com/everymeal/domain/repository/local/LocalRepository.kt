package com.everymeal.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun saveUniversity(index : Int, univName : String)

    suspend fun getUniversityIndex() : Flow<String>
}