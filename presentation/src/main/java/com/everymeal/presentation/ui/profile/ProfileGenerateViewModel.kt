package com.everymeal.presentation.ui.profile

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

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

class ProfileGenerateViewModel :
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

    }
}
