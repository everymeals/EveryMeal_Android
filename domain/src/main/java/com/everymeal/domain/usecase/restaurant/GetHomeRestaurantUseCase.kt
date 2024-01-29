package com.everymeal.domain.usecase.restaurant

import com.everymeal.domain.model.restaurant.GetUnivRestaurantEntity
import com.everymeal.domain.repository.restaurant.RestaurantRepository
import javax.inject.Inject

class GetHomeRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(
        campusIdx: Int,
        order: String,
        group: String?,
        grade: String?
    ) : Result<GetUnivRestaurantEntity> {
        return restaurantRepository.getHomeRestaurant(campusIdx, order, group, grade)
    }
}