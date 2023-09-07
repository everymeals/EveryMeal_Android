package com.everymeal.presentation.ui.save

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.everymeal.presentation.base.ViewEvent
import com.everymeal.presentation.base.ViewSideEffect
import com.everymeal.presentation.base.ViewState
import com.everymeal.presentation.ui.save.chip.ChipState

data class SaveState(
    val chipElements: List<ChipState> = listOf(
        ChipState("전체", mutableStateOf(true)),
        ChipState("밥집", mutableStateOf(false)),
        ChipState("카페", mutableStateOf(false)),
        ChipState("술", mutableStateOf(false))
    )
) : ViewState

sealed class SaveEvent : ViewEvent {
    data class OnChipClicked(
        val chipIndex: Int
    ) : SaveEvent()
}

/*
메인 화면으로 이동 ViewSideEffect
 */
sealed class SaveEffect : ViewSideEffect {
    object MoveToMain : SaveEffect()
}
