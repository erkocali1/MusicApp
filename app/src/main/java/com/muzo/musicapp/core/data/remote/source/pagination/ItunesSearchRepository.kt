package com.muzo.musicapp.core.data.remote.source.pagination

import androidx.paging.PagingData
import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.core.data.model.ResponsePagination
import kotlinx.coroutines.flow.Flow

interface ItunesSearchRepository {
    fun getSearchResult(
        searchWord: String,
        pageSize: Int = 9,
    ): Flow<PagingData<PaginationList>>
}