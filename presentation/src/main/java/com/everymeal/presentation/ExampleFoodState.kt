package com.everymeal.presentation

import com.everymeal.domain.model.ResponseWeekFoodEntity

sealed class ExampleFoodState {
    object UnInitialized : ExampleFoodState()

    object Loading : ExampleFoodState()

    data class SuccessWeekFoodGetData(val getWeekFoodData: ResponseWeekFoodEntity) : ExampleFoodState()

    data class Error(val errorCode: Int) : ExampleFoodState()
}