package com.everymeal.presentation.ui.signup

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.signup.UnivSelectContract.UnivSelectEffect
import com.everymeal.presentation.ui.signup.UnivSelectContract.UnivSelectEvent
import com.everymeal.presentation.ui.signup.UnivSelectContract.UnivSelectState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnivSelectViewModel @Inject constructor(

) : BaseViewModel<UnivSelectState, UnivSelectEffect, UnivSelectEvent>(
    UnivSelectState()
) {

    override fun handleEvents(event: UnivSelectEvent) {
        when (event) {
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
}