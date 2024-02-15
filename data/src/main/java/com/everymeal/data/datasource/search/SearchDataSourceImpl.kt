package com.everymeal.data.datasource.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.everymeal.data.model.restaruant.RestaurantResponse
import com.everymeal.data.service.search.SearchService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchService: SearchService,
) : SearchDataSource {
    override suspend fun searchRestraurant(
        campusIdx: Int,
        keyword: String,
    ): Flow<PagingData<RestaurantResponse>> {
        val pagingSourceFactory = {
            SearchPagingSource(
                searchService = searchService,
                campusIdx = campusIdx,
                keyword = keyword
            )
        }
        return Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}
