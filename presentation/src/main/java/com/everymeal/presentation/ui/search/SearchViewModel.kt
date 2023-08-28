package com.everymeal.presentation.ui.search

import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() :
    BaseViewModel<SearchState, SearchEffect, SearchEvent>(SearchState()) {
    init {
        updateState {
            copy(searchHistoryItems = listOf("test1", "test2", "test3"))
        }
    }

    override fun handleEvents(event: SearchEvent) {
        when (event) {
            is SearchEvent.SetShowSearchHistory -> {
                updateState {
                    copy(searchIsShowHistory = event.show)
                }
            }

            is SearchEvent.SearchQueryChanged -> {
                updateState {
                    copy(searchQuery = event.query)
                }
            }

            is SearchEvent.UpdateSearchHistory -> {
                updateState {
                    copy(searchHistoryItems = event.historyItems)
                }
            }
        }
    }
}
