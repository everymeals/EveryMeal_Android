package com.everymeal.domain.repository.search

import com.everymeal.domain.model.restaurant.RestaurantDataEntity

interface SearchRepository {
    suspend fun search(keyword: String): Result<List<RestaurantDataEntity>>
}
