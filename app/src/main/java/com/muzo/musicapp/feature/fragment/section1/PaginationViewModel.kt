package com.muzo.musicapp.feature.fragment.section1

import androidx.lifecycle.ViewModel

import androidx.paging.PagingData
import com.muzo.musicapp.core.data.model.PaginationList


import com.muzo.musicapp.core.data.remote.source.pagination.GetSearchResultUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PaginationViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {

    fun getSearchResult(searchWord: String): Flow<PagingData<PaginationList>> {
        return getSearchResultUseCase.invoke(searchWord)
    }

}