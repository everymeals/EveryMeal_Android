package com.everymeal.data.repository.search

import androidx.paging.PagingData
import androidx.paging.map
import com.everymeal.data.datasource.search.SearchDataSourceImpl
import com.everymeal.data.model.restaruant.toRestaurant
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.repository.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val searchDataSourceImpl: SearchDataSourceImpl,
) : SearchRepository {
    override suspend fun search(
        campusIdx: Int,
        keyword: String
    ): Flow<PagingData<RestaurantDataEntity>> {
        return searchDataSourceImpl.searchRestraurant(
            campusIdx = campusIdx,
            keyword = keyword,
        ).map { pagingData ->
            pagingData.map {
                it.toRestaurant()
            }
        }
    }
}
