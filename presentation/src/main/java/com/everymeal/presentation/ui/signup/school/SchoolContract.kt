package com.everymeal.presentation.ui.signup.school

import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class SchoolContract {

    data class State(
        val isShowConditionBottomSheet : Boolean = false,
        val isEmailError: Boolean = false,
        val emailLink: String = ""
    ) : ViewState

    sealed class Event : ViewEvent {
        data class OnEmailTextChanged(val emailLink: String) : Event()
        object OnNextButtonClicked : Event()
        object OnPostEmail : Event()
    }

    sealed class Effect : ViewSideEffect {
        object ShowConditionAgreeBottomSheet : Effect()
    }
}
