package com.everymeal.presentation.ui.signup

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class UnivSelectContract {

    data class UnivSelectState(
        val univSelectLoadState: LoadState = LoadState.SUCCESS,
        val selectedUniv: String = ""
    ) : ViewState

    sealed class UnivSelectEvent : ViewEvent {
        data class SelectedUniv(
            val selectedUniv: String
        ) : UnivSelectEvent()
    }

    sealed class UnivSelectEffect : ViewSideEffect {
        object MoveToMain: UnivSelectEffect()
        object UnivSelect: UnivSelectEffect()
    }
}