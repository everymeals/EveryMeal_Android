package com.everymeal.domain.usecase.restaurant

import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.repository.restaurant.RestaurantRepository
import javax.inject.Inject

class GetDetailRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(
        restaurantIdx: Int,
    ) : Result<RestaurantDataEntity> {
        return restaurantRepository.getRestaurantDetail(restaurantIdx)
    }
}