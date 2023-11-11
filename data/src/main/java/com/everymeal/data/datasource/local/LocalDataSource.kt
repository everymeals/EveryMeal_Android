package com.everymeal.data.datasource.local

interface LocalDataSource {
    suspend fun saveUniversity(index : Int, univName : String)
}