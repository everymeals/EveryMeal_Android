package com.everymeal.domain.usecase

import com.everymeal.domain.repository.FoodRepository
import javax.inject.Inject

class GetWeekFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(s : String) = foodRepository.weekFood(s)
}