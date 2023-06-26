package com.muzo.musicapp.core.data.remote.source.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.core.data.remote.api.ResultService
import retrofit2.HttpException

class MusicPagingSource(
    private val resultApiService: ResultService,
    private val searchWord: String,
    private val pageSize: Int
) : PagingSource<Int, PaginationList>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PaginationList> {

        val offset = params.key ?: STARTING_PAGE_OFFSET
        val limit = pageSize - 1
        return try {
            val response = resultApiService.pagination(
                searchWord = searchWord,
                limit = limit,
                offset = offset
            )
            val data = response.body()?.results ?: emptyList()
            val responseData = mutableListOf<PaginationList>()
            responseData.addAll(data)

            val nextKey = if (responseData.isEmpty()) null else offset + pageSize
            val prevKey = if (offset == STARTING_PAGE_OFFSET) null else offset

            LoadResult.Page(
                data = responseData, nextKey = nextKey, prevKey = prevKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)

        }
    }


    override fun getRefreshKey(state: PagingState<Int, PaginationList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(pageSize)

        }
    }

    companion object {
        private const val STARTING_PAGE_OFFSET = 0
        private const val MEDIA_TYPE_MUSIC = "music"
        private const val ENTITY_SONG = "song"

    }


}