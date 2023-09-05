package com.everymeal.presentation.ui.home

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.home.HomeContract.HomeEffect
import com.everymeal.presentation.ui.home.HomeContract.HomeState
import com.everymeal.presentation.ui.home.HomeContract.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class Review(
    val name: String,
    val profileImage: Int,
    val loveCount: Int,
    val image: List<Int>,
    val rating: Int,
    val reviewDate: String,
    val content: String,
    val restaurantName: String
)

data class Restaurant(
    val name: String,
    val category: String,
    val image: List<Int>,
    val rating: Double,
    val reviewCount: Int,
    val loveCount: Int,
)

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeState, HomeEffect, HomeEvent>(
    HomeState()
) {

    override fun handleEvents(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnClickDetailList -> {
                sendEffect({ HomeEffect.NavigateToDetailListScreen(event.detailListScreenType) })
            }
        }
    }
}