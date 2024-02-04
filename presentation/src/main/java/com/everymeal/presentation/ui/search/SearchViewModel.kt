package com.everymeal.presentation.ui.search

import androidx.lifecycle.viewModelScope
import com.everymeal.domain.repository.search.SearchRepository
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
) :
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

            is SearchEvent.SearchRestaurant -> {
                search()
            }
        }
    }

    private fun search() {
        viewModelScope.launch {
            val keyword = viewState.value.searchQuery
            if (keyword.isEmpty()) {
                return@launch
            }
            searchRepository.search(keyword).onSuccess { result ->
                updateState {
                    copy(
                        searchResultList = result,
                        searchIsShowHistory = false,
                    )
                }
            }
        }
    }
}
