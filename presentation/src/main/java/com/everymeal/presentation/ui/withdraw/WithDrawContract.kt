package com.everymeal.presentation.ui.withdraw

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class WithDrawContract {
    data class WithDrawState(
        val uiState: LoadState = LoadState.LOADING,
        val selectedReason: WithDrawReasonType? = null
    ) : ViewState

    sealed class WithDrawEvent : ViewEvent {
        data class WithDrawReasonSelected(val reason: WithDrawReasonType) : WithDrawEvent()
    }

    sealed class WithDrawEffect : ViewSideEffect {

    }
}

enum class WithDrawReasonType(val reason : String) {
    NOT_USE("앱을 잘 쓰지 않아요"),
    INCONVENIENT("사용성이 불편해요"),
    ERROR("오류가 자주 발생해요"),
    CHANGE_SCHOOL("학교가 바뀌었어요"),
    ETC("기타")
}