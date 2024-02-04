package com.everymeal.domain.usecase.restaurant

import androidx.paging.PagingData
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.repository.restaurant.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUnivRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(
        campusIdx: Int,
        order: String,
        group: String?,
        grade: String?
    ) : Flow<PagingData<RestaurantDataEntity>> {
        return restaurantRepository.getUnivRestaurant(campusIdx, order, group, grade)
    }
}
