package com.everymeal.domain.repository.search

import androidx.paging.PagingData
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(campusIdx: Int, keyword: String): Flow<PagingData<RestaurantDataEntity>>
}
