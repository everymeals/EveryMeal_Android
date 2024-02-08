package com.everymeal.presentation.ui.withdraw

import com.everymeal.presentation.base.BaseViewModel
import com.everymeal.presentation.ui.mypage.MyPageContract
import javax.inject.Inject

class WithDrawViewModel @Inject constructor(

) : BaseViewModel<WithDrawContract.WithDrawState, WithDrawContract.WithDrawEffect, WithDrawContract.WithDrawEvent>(
    WithDrawContract.WithDrawState()
) {
    override fun handleEvents(event: WithDrawContract.WithDrawEvent) {
        when(event) {
            is WithDrawContract.WithDrawEvent.WithDrawReasonSelected -> {
                updateState {
                    copy(
                        selectedReason = event.reason
                    )
                }
            }
        }
    }
}
