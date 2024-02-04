package com.everymeal.presentation.ui.mypage

import com.everymeal.presentation.base.LoadState
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState

class MyPageContract {
    data class MyPageState(
        val uiState: LoadState = LoadState.LOADING,
    ) : ViewState

    sealed class MyPageEvent : ViewEvent {

    }

    sealed class MyPageEffect : ViewSideEffect {

    }
}

enum class MyPageSetting(val type : String) {
    INQUIRY("문의하기"),
    SERVICE_TERMS("서비스 약관"),
    OPEN_SOURCE_LICENSE("오픈소스 라이센스"),
    VERSION_INFO("버전 정보"),
    WITHDRAWAL("탈퇴하기")
}