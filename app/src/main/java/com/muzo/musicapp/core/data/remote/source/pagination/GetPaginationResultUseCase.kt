package com.muzo.musicapp.core.data.remote.source.pagination

import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor (private val itunesSearchRepository: ItunesSearchRepository) {

    operator fun invoke(
        searchWord: String,
    ) = itunesSearchRepository.getSearchResult(
        searchWord
    )
}