package com.everymeal.presentation.ui.profile

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.model.auth.UserSignUp
import com.everymeal.domain.repository.auth.UsersRepository
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

data class ProfileGenerateState(
    val nickname: String = "",
    val isNicknameValid: Boolean = false,

    ) : ViewState

sealed interface ProfileGenerateEvent : ViewEvent {
    data class OnNicknameChanged(
        val nickname: String
    ) : ProfileGenerateEvent

    object OnCompleteButtonClicked : ProfileGenerateEvent
}


sealed interface ProfileGenerateEffect : ViewSideEffect {

}

@HiltViewModel
class ProfileGenerateViewModel(
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
            )
        }
    }
}
