package com.everymeal.presentation.ui.signup.school

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.auth.Email
import com.everymeal.domain.usecase.auth.PostEmailUseCase
import com.everymeal.domain.usecase.auth.VerifyTokenUseCase
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SchoolAuthViewModel @Inject constructor(
    private val postEmailUseCase: PostEmailUseCase,
    private val verifyTokenUseCase: VerifyTokenUseCase
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
                    copy(emailAuthValue = event.authValue)
                }
            }

            is SchoolContract.Event.OnEmailNextButtonClicked -> {
                if (!viewState.value.isEmailError) {
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
                verifyToken()
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
                    copy(
                        emailAuthToken = it.emailAuthToken,
                        schoolAuthScreenType = SchoolAuthScreenType.VERIFY_TOKEN,
                        isShowConditionBottomSheet = false
                    )
                }
            }.onFailure {
                Log.d("SchoolAuthViewModel", "postEmail: $it")
                when (it) {
                    is HttpException -> sendEffect({ SchoolContract.Effect.Error(code = it.code()) })
                    else -> sendEffect({ SchoolContract.Effect.Error(message = it.message) })
                }
                updateState {
                    copy(
                        isShowConditionBottomSheet = false
                    )
                }
            }
        }
    }

    private fun verifyToken() {
        viewModelScope.launch {
            val state = viewState.value
            verifyTokenUseCase(
                emailAuthToken = state.emailAuthToken,
                emailAuthValue = state.emailAuthValue
            ).onSuccess {
                Log.d("SchoolAuthViewModel", "postEmail: $it")
                sendEffect({ SchoolContract.Effect.SuccessEmailVerification })
            }.onFailure {
                Log.d("SchoolAuthViewModel", "postEmail: $it")
                when (it) {
                    is HttpException -> sendEffect({ SchoolContract.Effect.Error(code = it.code()) })
                    else -> sendEffect({ SchoolContract.Effect.Error(message = it.message) })
                }
            }
        }
    }
}
