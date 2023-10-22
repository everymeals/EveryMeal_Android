package com.everymeal.presentation.ui.signup.school

import com.everymeal.presentation.base.BaseViewModel

class SchoolAuthViewModel :
    BaseViewModel<SchoolContract.State, SchoolContract.Effect, SchoolContract.Event>(SchoolContract.State()) {

    companion object {
        private val EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    }

    override fun handleEvents(event: SchoolContract.Event) {
        when (event) {
            is SchoolContract.Event.OnEmailTextChanged -> {
                updateState {
                    copy(
                        isEmailError = isValidEmail(event.emailLink),
                        emailLink = event.emailLink
                    )
                }
            }
        }
    }


    private fun isValidEmail(email: String): Boolean {
        return EMAIL_REGEX.matches(email)
    }
}
