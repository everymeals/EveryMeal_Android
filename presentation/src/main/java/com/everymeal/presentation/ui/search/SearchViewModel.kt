package com.everymeal.presentation.ui.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.everymeal.domain.repository.search.SearchRepository
import com.everymeal.domain.usecase.local.GetUniversityIndexUseCase
import com.everymeal.domain.usecase.onboarding.GetUniversityUseCase
import com.everymeal.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val getUniversityIndexUseCase: GetUniversityIndexUseCase,
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
            val campusIdx = getUniversityIndexUseCase().first().toInt()
            if (keyword.isEmpty()) {
                return@launch
            }

            val searchResultList = searchRepository.search(campusIdx, keyword).cachedIn(viewModelScope)
                updateState {
                    copy(
                        searchResultList = searchResultList,
                        searchIsShowHistory = false,
                    )
                }

        }
    }
}
