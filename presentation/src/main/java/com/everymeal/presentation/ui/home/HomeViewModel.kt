package com.everymeal.presentation.ui.home

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.home.HomeContract.HomeEffect
import com.everymeal.presentation.ui.home.HomeContract.HomeState
import com.everymeal.presentation.ui.home.HomeContract.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeState, HomeEffect, HomeEvent>(
    HomeState()
) {

    override fun handleEvents(event: HomeEvent) {

    }
}