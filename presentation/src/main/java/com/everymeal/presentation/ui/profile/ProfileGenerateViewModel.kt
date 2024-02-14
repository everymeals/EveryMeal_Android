package com.everymeal.presentation.ui.profile

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.NetworkPreference
import com.everymeal.domain.model.auth.UserSignUp
import com.everymeal.domain.repository.auth.UsersRepository
import com.everymeal.domain.usecase.local.GetUniversityIndexUseCase
import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import com.everymeal.presentation.util.toHttpErrorCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileGenerateState(
    val nickname: String = "",
    val isNicknameValid: Boolean = false,
    val isShowProfileChangeBottomSheet: Boolean = false,
    val profileImageKey: String = "user/f5f257c7-1ff7-4959-b537-bb4035abcb59",
    val profileImageUrl: String = "",
    val emailAuthToken: String = "",
    val emailAuthValue: String = ""
) : ViewState

sealed interface ProfileGenerateEvent : ViewEvent {
    data class OnNicknameChanged(
        val nickname: String
    ) : ProfileGenerateEvent

    object OnCompleteButtonClicked : ProfileGenerateEvent
    object ShowProfileChangeBottomSheet : ProfileGenerateEvent
    object HideProfileChangeBottomSheet : ProfileGenerateEvent
    data class SetProfileImageDefaultKey(val imageKey: String) : ProfileGenerateEvent
    data class SetEmailAuth(
        val emailAuthValue: String,
        val emailAuthToken: String
    ) : ProfileGenerateEvent
}


sealed interface ProfileGenerateEffect : ViewSideEffect {
    object SignUpSuccess : ProfileGenerateEffect
    data class SignUpFailure(val errorCode: Int) : ProfileGenerateEffect
}

@HiltViewModel
class ProfileGenerateViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val getUniversityIndexUseCase: GetUniversityIndexUseCase,
    private val networkPreference: NetworkPreference
) : BaseViewModel<ProfileGenerateState, ProfileGenerateEffect, ProfileGenerateEvent>(
    ProfileGenerateState()
) {
    init {
        updateState {
            copy(profileImageUrl = networkPreference.profileImgUrl)
        }
    }

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

            is ProfileGenerateEvent.SetProfileImageDefaultKey -> {
                updateState {
                    copy(profileImageKey = event.imageKey)
                }
            }

            is ProfileGenerateEvent.SetEmailAuth -> {
                updateState {
                    copy(
                        emailAuthToken = event.emailAuthToken,
                        emailAuthValue = event.emailAuthValue
                    )
                }
            }
        }
    }

    private fun generateProfile() {
        val state = viewState.value
        viewModelScope.launch {
            usersRepository.signUp(
                UserSignUp(
                    emailAuthToken = state.emailAuthToken,
                    emailAuthValue = state.emailAuthValue,
                    nickname = state.nickname,
                    profileImgKey = state.profileImageKey,
                    universityIdx = getUniversityIndexUseCase().firstOrNull()?.toInt() ?: -1
                )
            ).onSuccess {
                networkPreference.run {
                    accessToken = it.accessToken
                    refreshToken = it.refreshToken
                    profileImgUrl = it.profileImg
                }
                sendEffect({ ProfileGenerateEffect.SignUpSuccess })
            }.onFailure {
                val errorCode = it.toHttpErrorCode()
                sendEffect({ ProfileGenerateEffect.SignUpFailure(errorCode) })
            }
        }
    }
}
