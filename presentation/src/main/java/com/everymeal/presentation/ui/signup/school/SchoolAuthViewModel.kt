package com.everymeal.presentation.ui.signup.school

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.usecase.auth.PostEmailUseCase
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SchoolAuthViewModel @Inject constructor(
    private val postEmailUseCase: PostEmailUseCase
) :
    BaseViewModel<SchoolContract.State, SchoolContract.Effect, SchoolContract.Event>(SchoolContract.State()) {

    companion object {
        private val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
    }

    override fun handleEvents(event: SchoolContract.Event) {
        when (event) {
            is SchoolContract.Event.OnEmailTextChanged -> {
                updateState {
                    copy(
                        isEmailError = isValidEmail(event.emailLink),
                        emailText = event.emailLink
                    )
                }
            }

            is SchoolContract.Event.OnTokenTextChanged -> {
                updateState {
                    copy(
                        tokenText = event.token
                    )
                }
            }

            is SchoolContract.Event.OnEmailNextButtonClicked -> {
                if (viewState.value.isEmailError) {
                    // TODO 이메일 에러
                    sendEffect({ SchoolContract.Effect.Error(400) })
                } else {
                    updateState {
                        copy(isShowConditionBottomSheet = true)
                    }
                }
            }

            is SchoolContract.Event.OnPostEmail -> {
                postEmail()
            }

            SchoolContract.Event.FailEmailVerification -> {
                sendEffect()
            }

            SchoolContract.Event.OnTokenNextButtonClicked -> {
                val viewState = viewState.value
                if (viewState.emailAuthToken == viewState.tokenText) {
                    sendEffect({ SchoolContract.Effect.SuccessEmailVerification })
                }
            }
        }
    }


    private fun isValidEmail(email: String): Boolean {
        return !EMAIL_REGEX.matches(email)
    }

    private fun postEmail() {
        viewModelScope.launch {
            postEmailUseCase(Email(viewState.value.emailText)).onSuccess {
                Log.d("SchoolAuthViewModel", "postEmail: $it")
                updateState {
                    copy(emailAuthToken = it.emailAuthToken)
                }
            }.onFailure {
                Log.d("SchoolAuthViewModel", "postEmail: $it")
                if (it is HttpException) {
                    sendEffect({ SchoolContract.Effect.Error(it.code()) })
                }
            }
        }
    }
}
