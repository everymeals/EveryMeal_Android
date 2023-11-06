package com.everymeal.presentation.ui.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.everymeal.domain.usecase.onboarding.GetUniversityUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.ui.signup.UnivSelectContract.UnivSelectEffect
import com.everymeal.presentation.ui.signup.UnivSelectContract.UnivSelectEvent
import com.everymeal.presentation.ui.signup.UnivSelectContract.UnivSelectState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnivSelectViewModel @Inject constructor(
    private val getUniversityUseCase: GetUniversityUseCase
) : BaseViewModel<UnivSelectState, UnivSelectEffect, UnivSelectEvent>(
    UnivSelectState()
) {

    override fun handleEvents(event: UnivSelectEvent) {
        when (event) {
            is UnivSelectEvent.InitUnivSelectScreen -> {
                updateState { copy(univSelectLoadState = LoadState.LOADING) }
                getUniversity()
            }

            is UnivSelectEvent.SelectButtonClicked -> {
                sendEffect({ UnivSelectEffect.MoveToMain })
            }

            is UnivSelectEvent.SelectedUniv -> {
                updateState {
                    copy(
                        selectedUniv = event.selectedUniv
                    )
                }
            }
        }
    }

    private fun getUniversity() {
        viewModelScope.launch {
            getUniversityUseCase().onSuccess {
                updateState {
                    copy(
                        univSelectLoadState = LoadState.SUCCESS,
                        universities = it.data
                    )
                }
            }.onFailure {
                updateState {
                    copy(
                        univSelectLoadState = LoadState.ERROR,
                    )
                }
            }
        }
    }
}