package com.everymeal.data.repository.search

import com.everymeal.data.model.restaruant.toRestaurants
import com.everymeal.data.service.search.SearchService
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.repository.search.SearchRepository
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val searchService: SearchService,
) : SearchRepository {
    override suspend fun search(keyword: String): Result<List<RestaurantDataEntity>> {
        return runCatching {
            searchService.search(keyword).toRestaurants()
        }
    }
}
