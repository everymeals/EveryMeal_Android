package com.everymeal.presentation.ui.signup

import com.everymeal.domain.model.onboarding.GetUniversityEntity.UniversityData
import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class UnivSelectContract {

    /*
    대학교 불러오기 LoadState
    대학교 선택하기 State Hoisting
     */
    data class UnivSelectState(
        val univSelectLoadState: LoadState = LoadState.LOADING,
        val selectedUniv: String = "",
        val universities: List<UniversityData> = emptyList(),
        val networkErrorDialog: Boolean = true,
        val univIdx: Int = 0,
        val univSelectFullName: String = "",
        val campusName: String = ""
    ) : ViewState

    /*
    선택하기 버튼 ViewEvent
    대학교 선택하기 ViewEvent
     */
    sealed class UnivSelectEvent : ViewEvent {
        object InitUnivSelectScreen : UnivSelectEvent()
        data class SelectButtonClicked(
            val univIdx: Int,
            val univSelectFullName: String,
            val campusName: String
        ) : UnivSelectEvent()
        data class SelectedUniv(
            val selectedUniv: String,
            val univIdx: Int,
            val univSelectFullName: String,
            val campusName: String
        ) : UnivSelectEvent()
        data class NetworkErrorDialogClicked(
            val dialogStateChange: Boolean
        ) : UnivSelectEvent()
    }

    /*
    메인 화면으로 이동 ViewSideEffect
     */
    sealed class UnivSelectEffect : ViewSideEffect {
        object MoveToMain: UnivSelectEffect()
    }
}