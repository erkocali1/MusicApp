package com.muzo.musicapp.feature.fragment.section1

import androidx.lifecycle.ViewModel

import androidx.paging.PagingData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.core.data.model.PaginationList


import com.muzo.musicapp.core.data.remote.source.pagination.GetSearchResultUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class PaginationViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val localMusicDataSource: LocalMusicDataSource
) : ViewModel() {

    fun getSearchResult(searchWord: String): Flow<PagingData<PaginationList>> {
        return getSearchResultUseCase.invoke(searchWord)
    }

    fun convertToMusicLocalDataList(music: PaginationList): List<LastClikedMusic> {
        return listOf(
            LastClikedMusic(
                uid = 0, // Otomatik oluşturulan bir uid değeri atanacak
                artistName = music.artistName,
                trackName = music.trackName,
                releaseDate = music.releaseDate,
                trackPrice = music.trackPrice.toString(),
                artworkUrl100 = music.artworkUrl100,
                previewUrl = music.previewUrl,
                collectionName = music.collectionName,

                )
        )
    }
    suspend fun saveLastClicked(item:List<LastClikedMusic>){
        localMusicDataSource.insertLastClickedMusic(item)
    }

}