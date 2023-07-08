package com.everymeal.data.repository

import com.everymeal.data.model.toWeekFoodEntity
import com.everymeal.data.service.ExampleApi
import com.everymeal.domain.model.ResponseWeekFoodEntity
import com.everymeal.domain.repository.FoodRepository
import com.everymeal.domain.model.Result
import javax.inject.Inject


class FoodRepositoryImpl @Inject constructor(
    private val exampleApi: ExampleApi
) : FoodRepository {
    override suspend fun weekFood(area: String): Result<ResponseWeekFoodEntity> {
        val response = exampleApi.weekGetFoodArea(area)
        return try {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    return Result.Success(data.toWeekFoodEntity())
                } ?: Result.Error(IllegalArgumentException("주간 급식 정보를 찾을 수 없습니다."))
            } else {
                Result.Error(IllegalArgumentException("네트워크 에러가 발생하였습니다."))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}