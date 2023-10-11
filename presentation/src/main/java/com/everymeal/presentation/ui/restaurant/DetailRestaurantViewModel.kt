package com.everymeal.presentation.ui.restaurant

import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(

): BaseViewModel<DetailRestaurantState, DetailRestaurantEffect, DetailRestaurantEvent>(
    DetailRestaurantState()
) {

    override fun handleEvents(event: DetailRestaurantEvent) {
        when(event) {
            is DetailRestaurantEvent.OnTabSelectedChanged -> {
                reflectUpdateState(
                    selectedTabIndex = event.selectedTabIndex
                )
            }
            is DetailRestaurantEvent.OnFloatingButtonClick -> {
                reflectUpdateState(
                    isFabClicked = event.isFabClicked
                )
            }
        }
    }

    private fun reflectUpdateState(
        selectedTabIndex: Int = viewState.value.selectedTabIndex,
        isFabClicked: Boolean = viewState.value.isFabClicked,
    ) {
        updateState {
            copy(
                selectedTabIndex = selectedTabIndex,
                isFabClicked = isFabClicked,
            )
        }
    }
}