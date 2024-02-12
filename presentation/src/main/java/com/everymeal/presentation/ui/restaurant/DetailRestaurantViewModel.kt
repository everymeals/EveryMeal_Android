package com.everymeal.presentation.ui.restaurant

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.everymeal.domain.model.restaurant.RestaurantDataEntity
import com.everymeal.domain.model.review.StoreReviewEntity
import com.everymeal.domain.usecase.restaurant.GetDetailRestaurantUseCase
import com.everymeal.domain.usecase.review.GetStoreReviewUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRestaurantViewModel @Inject constructor(
    private val getDetailRestaurantUseCase: GetDetailRestaurantUseCase,
    private val getStoreReviewUseCase: GetStoreReviewUseCase
): BaseViewModel<DetailRestaurantState, DetailRestaurantEffect, DetailRestaurantEvent>(
    DetailRestaurantState()
) {
    private val _restaurantReviews : MutableStateFlow<PagingData<StoreReviewEntity>> = MutableStateFlow(value = PagingData.empty())
    val restaurantReviews : StateFlow<PagingData<StoreReviewEntity>> = _restaurantReviews.asStateFlow()

    override fun handleEvents(event: DetailRestaurantEvent) {
        when(event) {
            is DetailRestaurantEvent.InitDetailRestaurantScreen -> {
                getDetailRestaurant(event.restaurantId)
                getReviewList()
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

    private fun getReviewList() {
        viewModelScope.launch {
            getStoreReviewUseCase(
                order = "name",
                group = null,
                grade = null,
                campusIdx = 2
            ).cachedIn(viewModelScope)
                .collect {
                    _restaurantReviews.emit(it)
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
        restaurantInfo: RestaurantDataEntity = viewState.value.restaurantInfo,
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
