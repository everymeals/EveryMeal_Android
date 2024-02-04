package com.everymeal.domain.repository.search

import com.everymeal.domain.model.restaurant.Restaurant

interface SearchRepository {
    suspend fun search(keyword: String): Result<List<Restaurant>>
}
