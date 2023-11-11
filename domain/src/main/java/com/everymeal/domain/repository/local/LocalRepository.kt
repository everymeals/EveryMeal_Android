package com.everymeal.domain.repository.local

interface LocalRepository {
    suspend fun saveUniversity(index : Int, univName : String)
}