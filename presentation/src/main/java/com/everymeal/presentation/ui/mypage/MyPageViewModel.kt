package com.everymeal.presentation.ui.mypage

import com.everymeal.presentation.base.BaseViewModel
import javax.inject.Inject

class MyPageViewModel @Inject constructor(

): BaseViewModel<MyPageContract.MyPageState, MyPageContract.MyPageEffect, MyPageContract.MyPageEvent>(
    MyPageContract.MyPageState()
) {

    override fun handleEvents(event: MyPageContract.MyPageEvent) {

    }
}

