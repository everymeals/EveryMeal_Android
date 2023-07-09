package com.everymeal.data.datasource

import com.everymeal.domain.model.ResponseWeekFoodEntity
import com.everymeal.domain.model.Result

interface FoodDataSource {

    suspend fun weekGetFoodArea(s: String): Result<ResponseWeekFoodEntity>
}