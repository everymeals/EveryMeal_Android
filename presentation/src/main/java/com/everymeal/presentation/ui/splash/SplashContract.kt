package com.everymeal.presentation.ui.splash

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class SplashContract {
    data class SplashViewState(
        val loginState: LoadState = LoadState.SUCCESS,
        val selectedUnitIndex: String? = null,
    ) : ViewState

    sealed class SplashSideEffect : ViewSideEffect

    sealed class SplashEvent : ViewEvent {
        object InitSplashScreen: SplashEvent()
    }
}
