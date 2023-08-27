package com.everymeal.presentation.ui.save

import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SaveScreenViewModel @Inject constructor() :
    BaseViewModel<SaveState, SaveEffect, SaveEvent>(SaveState()) {

    override fun handleEvents(event: SaveEvent) {
        when (event) {
            is SaveEvent.OnChipClicked -> {
                updateState {
                    chipElements.mapIndexed { index, chipState ->
                        chipState.isSelected.value = index == event.chipIndex
                    }
                    copy(
                        chipElements = chipElements
                    )
                }
            }
        }
    }
}
