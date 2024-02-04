package com.everymeal.presentation.ui.restaurant

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.restaurant.Restaurant
import com.everymeal.domain.usecase.restaurant.GetDetailRestaurantUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(
    private val getDetailRestaurantUseCase: GetDetailRestaurantUseCase
): BaseViewModel<DetailRestaurantState, DetailRestaurantEffect, DetailRestaurantEvent>(
    DetailRestaurantState()
) {
    override fun handleEvents(event: DetailRestaurantEvent) {
        when(event) {
            is DetailRestaurantEvent.InitDetailRestaurantScreen -> {
                getDetailRestaurant(event.restaurantId)
            }
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
            is DetailRestaurantEvent.NetworkErrorDialogClicked -> {
                reflectUpdateState(
                    networkErrorDialog = event.dialogStateChange
                )
            }
        }
    }

    private fun getDetailRestaurant(restaurantIdx: Int) {
        viewModelScope.launch {
            getDetailRestaurantUseCase(restaurantIdx).onSuccess {
                reflectUpdateState(
                    getDetailRestaurantState = LoadState.SUCCESS,
                    restaurantInfo = it
                )
            }.onFailure {
                reflectUpdateState(
                    getDetailRestaurantState = LoadState.ERROR
                )
            }
        }
    }

    private fun reflectUpdateState(
        selectedTabIndex: Int = viewState.value.selectedTabIndex,
        isFabClicked: Boolean = viewState.value.isFabClicked,
        restaurantInfo: Restaurant = viewState.value.restaurantInfo,
        getDetailRestaurantState: LoadState = viewState.value.getDetailRestaurantState,
        networkErrorDialog: Boolean = viewState.value.networkErrorDialog
    ) {
        updateState {
            copy(
                selectedTabIndex = selectedTabIndex,
                isFabClicked = isFabClicked,
                restaurantInfo = restaurantInfo,
                getDetailRestaurantState = getDetailRestaurantState,
                networkErrorDialog = networkErrorDialog
            )
        }
    }
}
