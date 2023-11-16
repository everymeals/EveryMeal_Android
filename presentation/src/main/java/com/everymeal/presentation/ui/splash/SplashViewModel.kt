package com.everymeal.presentation.ui.splash

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.splash.SplashContract.SplashEvent
import com.everymeal.presentation.ui.splash.SplashContract.SplashSideEffect
import com.everymeal.presentation.ui.splash.SplashContract.SplashViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : BaseViewModel<SplashViewState, SplashSideEffect, SplashEvent>(
    SplashViewState()
) {

    override fun handleEvents(event: SplashEvent) {

    }
}
