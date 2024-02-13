package com.everymeal.presentation.ui.signup.school

import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class SchoolContract {

    data class State(
        val schoolAuthScreenType: SchoolAuthScreenType = SchoolAuthScreenType.POST_EMAIL,
        val isShowConditionBottomSheet: Boolean = false,
        val isEmailError: Boolean = false,
        val emailText: String = "",
        val emailAuthValue: String = "",
        val emailAuthToken: String = ""
    ) : ViewState

    sealed class Event : ViewEvent {
        data class OnEmailTextChanged(val emailLink: String) : Event()
        data class OnTokenTextChanged(val authValue: String) : Event()
        object OnEmailNextButtonClicked : Event()
        object OnTokenNextButtonClicked : Event()
        object OnPostEmail : Event()
        object FailEmailVerification : Event()
    }

    sealed class Effect : ViewSideEffect {
        data class Error(
            val code: Int? = null,
            val message: String? = null
        ) : Effect()

        data class SuccessEmailVerification(
            val emailAuthValue: String,
            val emailAuthToken: String
        ) : Effect()
    }
}
