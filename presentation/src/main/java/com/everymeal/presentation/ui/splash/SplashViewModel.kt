package com.everymeal.presentation.ui.splash

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.usecase.local.GetUniversityIndexUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.ui.splash.SplashContract.SplashEvent
import com.everymeal.presentation.ui.splash.SplashContract.SplashSideEffect
import com.everymeal.presentation.ui.splash.SplashContract.SplashViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUniversityIndexUseCase: GetUniversityIndexUseCase
) : BaseViewModel<SplashViewState, SplashSideEffect, SplashEvent>(
    SplashViewState()
) {

    override fun handleEvents(event: SplashEvent) {
        when (event) {
            is SplashEvent.InitSplashScreen -> {
                getUniversityIndex()
            }
        }
    }

    private fun getUniversityIndex() {
        viewModelScope.launch {
            val univIndex = getUniversityIndexUseCase().first()
            updateState {
                copy(
                    selectedUnitIndex = univIndex,
                )
            }
        }
    }
}
