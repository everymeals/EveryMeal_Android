package com.everymeal.data.model

import com.everymeal.domain.model.ResponseWeekFoodEntity
import kotlinx.serialization.Serializable

@Serializable
data class WeekFoodResponse(
    val success : Boolean,
    val message : String,
    val localDateTime : String,
    val httpStatus : String,
    val httpCode : Int,
    val data : List<WeekFoodResult>,
) {
    @Serializable
    data class WeekFoodResult(
        val mealId: Int,
        val toDay: String,
        val mealType : String,
        val statusType : String,
        val meals : List<String>
    )
}

fun WeekFoodResponse.toWeekFoodEntity(): ResponseWeekFoodEntity {
    val weekFoodResultEntityList = this.data.map { result ->
        ResponseWeekFoodEntity.WeekFoodResultEntity(
            mealId = result.mealId,
            toDay = result.toDay,
            mealType = result.mealType,
            statusType = result.statusType,
            meals = result.meals
        )
    }

    return ResponseWeekFoodEntity(
        success = this.success,
        message = this.message,
        localDateTime = this.localDateTime,
        httpStatus = this.httpStatus,
        httpCode = this.httpCode,
        data = weekFoodResultEntityList
    )
}