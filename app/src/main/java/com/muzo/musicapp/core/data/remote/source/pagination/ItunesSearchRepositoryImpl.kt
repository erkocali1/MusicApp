package com.muzo.musicapp.core.data.remote.source.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.core.data.remote.api.ResultService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItunesSearchRepositoryImpl @Inject constructor (
    private val resultServiceApi:ResultService
):ItunesSearchRepository {
    override fun getSearchResult(searchWord: String, pageSize: Int): Flow<PagingData<PaginationList>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MusicPagingSource(
                    searchWord = searchWord,
                    resultApiService = resultServiceApi,
                    pageSize = pageSize
                )
            }
        ).flow
    }

}