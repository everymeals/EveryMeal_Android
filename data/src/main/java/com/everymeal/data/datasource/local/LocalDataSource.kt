package com.everymeal.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveUniversity(index : Int, univName : String)

    suspend fun getUniversityIndex() : Flow<String>
}