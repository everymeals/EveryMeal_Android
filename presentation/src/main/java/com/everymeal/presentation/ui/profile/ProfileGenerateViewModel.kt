package com.everymeal.presentation.ui.profile

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.auth.UserSignUp
import com.everymeal.domain.repository.auth.UsersRepository
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import com.everymeal.presentation.util.toHttpErrorCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileGenerateState(
    val nickname: String = "",
    val isNicknameValid: Boolean = false,
    val isShowProfileChangeBottomSheet: Boolean = false
) : ViewState

sealed interface ProfileGenerateEvent : ViewEvent {
    data class OnNicknameChanged(
        val nickname: String
    ) : ProfileGenerateEvent

    object OnCompleteButtonClicked : ProfileGenerateEvent
    object ShowProfileChangeBottomSheet : ProfileGenerateEvent
    object HideProfileChangeBottomSheet : ProfileGenerateEvent
}


sealed interface ProfileGenerateEffect : ViewSideEffect {
    object SignUpSuccess : ProfileGenerateEffect
    data class SignUpFailure(val errorCode: Int) : ProfileGenerateEffect

}

@HiltViewModel
class ProfileGenerateViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) :
    BaseViewModel<ProfileGenerateState, ProfileGenerateEffect, ProfileGenerateEvent>(
        ProfileGenerateState()
    ) {
    override fun handleEvents(event: ProfileGenerateEvent) {
        when (event) {
            is ProfileGenerateEvent.OnNicknameChanged -> {
                updateState {
                    copy(
                        nickname = event.nickname,
                        isNicknameValid = event.nickname.length >= 2
                    )
                }
            }

            ProfileGenerateEvent.OnCompleteButtonClicked -> {
                generateProfile()
            }

            ProfileGenerateEvent.ShowProfileChangeBottomSheet -> {
                updateState {
                    copy(isShowProfileChangeBottomSheet = true)
                }
            }

            ProfileGenerateEvent.HideProfileChangeBottomSheet -> {
                updateState {
                    copy(isShowProfileChangeBottomSheet = false)
                }
            }
        }
    }

    private fun generateProfile() {
        val state = viewState.value
        viewModelScope.launch {
            usersRepository.signUp(
                UserSignUp(
                    emailAuthToken = "",
                    emailAuthValue = "",
                    nickname = state.nickname,
                    profileImgKey = "",
                    universityIdx = 0
                )
            ).onSuccess {
                sendEffect({ ProfileGenerateEffect.SignUpSuccess })
            }.onFailure {
                val errorCode = it.toHttpErrorCode()
                sendEffect({ ProfileGenerateEffect.SignUpFailure(errorCode) })
            }
        }
    }
}
