package com.everymeal.presentation.ui.save

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.everymeal.presentation.ui.save.chip.ChipState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveScreenViewModel @Inject constructor() : ViewModel() {
    val elements = mutableStateListOf(
        ChipState("전체", mutableStateOf(true)),
        ChipState("밥집", mutableStateOf(false)),
        ChipState("카페", mutableStateOf(false)),
        ChipState("술", mutableStateOf(false))
    )

    fun updateChipState(chipIndex: Int) {
        elements.forEachIndexed { index, chipState ->
            chipState.isSelected.value = index == chipIndex
        }
    }
}