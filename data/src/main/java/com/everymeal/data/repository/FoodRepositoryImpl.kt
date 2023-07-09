package com.everymeal.data.repository

import com.everymeal.data.datasource.FoodDataSource
import com.everymeal.domain.model.ResponseWeekFoodEntity
import com.everymeal.domain.model.Result
import com.everymeal.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDataSource: FoodDataSource
) : FoodRepository {

    override suspend fun weekFood(area: String): Result<ResponseWeekFoodEntity> {
        return foodDataSource.weekGetFoodArea(area)
    }
}