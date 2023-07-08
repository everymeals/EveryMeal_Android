package com.everymeal.domain.repository

import com.everymeal.domain.model.ResponseWeekFoodEntity
import com.everymeal.domain.model.Result

interface FoodRepository {
    suspend fun weekFood(area: String): Result<ResponseWeekFoodEntity>
}