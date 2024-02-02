package com.everymeal.presentation.ui.search

import androidx.lifecycle.viewModelScope
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            is SearchEvent.SearchQueryChanged -> {
                updateState {
                    copy(
                        searchQuery = event.query,
                        searchIsShowHistory = event.query.isEmpty(),
                    )
                }
            }

            is SearchEvent.UpdateSearchHistory -> {
                updateState {
                    copy(searchHistoryItems = event.historyItems)
                }
            }
            is SearchEvent.SearchResultLoaded -> {

                updateState {
                    copy(
                        searchResultList = event.searchResultList,
                        searchIsShowHistory = false,
                    )
                }
            }
        }
    }

    private fun search() {
        viewModelScope.launch {
//            val result = repository.getUnivRestaurant(1, "test")
//            setEvent(SearchEvent.SearchResultLoaded(result))
        }
    }
}
