package com.everymeal.domain.usecase.restaurant

import com.everymeal.domain.model.restaurant.Restaurant
import com.everymeal.domain.repository.restaurant.RestaurantRepository
import javax.inject.Inject

class GetDetailRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(
        restaurantIdx: Int,
    ): Result<Restaurant> {
        return restaurantRepository.getRestaurantDetail(restaurantIdx)
    }
}
