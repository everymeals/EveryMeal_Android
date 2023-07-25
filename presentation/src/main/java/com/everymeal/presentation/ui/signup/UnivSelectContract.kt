package com.everymeal.presentation.ui.signup

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
        val univSelectLoadState: LoadState = LoadState.SUCCESS,
        val selectedUniv: String = ""
    ) : ViewState

    /*
    선택하기 버튼 ViewEvent
    대학교 선택하기 ViewEvent
     */
    sealed class UnivSelectEvent : ViewEvent {
        object SelectButtonClicked : UnivSelectEvent()
        data class SelectedUniv(
            val selectedUniv: String
        ) : UnivSelectEvent()
    }

    /*
    메인 화면으로 이동 ViewSideEffect
     */
    sealed class UnivSelectEffect : ViewSideEffect {
        object MoveToMain: UnivSelectEffect()
    }
}