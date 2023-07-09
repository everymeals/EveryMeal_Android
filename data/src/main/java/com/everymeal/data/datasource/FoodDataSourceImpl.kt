package com.everymeal.data.datasource

import com.everymeal.data.model.toWeekFoodEntity
import com.everymeal.data.service.ExampleApi
import com.everymeal.domain.model.ResponseWeekFoodEntity
import javax.inject.Inject
import com.everymeal.domain.model.Result

class FoodDataSourceImpl @Inject constructor(
    private val exampleApi: ExampleApi
) : FoodDataSource {
    override suspend fun weekGetFoodArea(s: String): Result<ResponseWeekFoodEntity> {
        val response = exampleApi.weekGetFoodArea(s)
        return try {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    return Result.Success(data.toWeekFoodEntity())
                } ?: Result.Error(404)
            } else {
                Result.Error(response.code())
            }
        } catch (e: Exception) {
            Result.Error(response.code())
        }
    }
}