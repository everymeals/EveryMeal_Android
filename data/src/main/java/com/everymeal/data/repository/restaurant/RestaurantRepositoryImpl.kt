package com.everymeal.data.repository.restaurant

import androidx.paging.PagingData
import androidx.paging.map
import com.everymeal.data.datasource.restaurant.RestaurantDataSource
import com.everymeal.data.model.restaruant.toGetUnivRestaurantEntity
import com.everymeal.data.model.restaruant.toRestaurant
import com.everymeal.domain.model.restaurant.GetUnivRestaurantEntity
import com.everymeal.domain.model.restaurant.Restaurant
import com.everymeal.domain.repository.restaurant.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val restaurantDataSource: RestaurantDataSource
) : RestaurantRepository {
    override suspend fun getUnivRestaurant(
        campusIdx: Int,
        order: String,
        group: String?,
        grade: String?
    ): Flow<PagingData<Restaurant>> {
        return restaurantDataSource.getUnivRestaurant(campusIdx, order, group, grade)
            .map { pagingData ->
                pagingData.map { it.toRestaurant() }
            }
    }

    override suspend fun getRestaurantDetail(index: Int): Result<Restaurant> {
        return restaurantDataSource.getRestaurantDetail(index).map { it.toRestaurant() }
    }

    override suspend fun getHomeRestaurant(
        campusIdx: Int,
        order: String,
        group: String?,
        grade: String?
    ): Result<GetUnivRestaurantEntity> {
        return restaurantDataSource.getHomeRestaurant(campusIdx, order, group, grade)
            .map { it.toGetUnivRestaurantEntity() }
    }
}
