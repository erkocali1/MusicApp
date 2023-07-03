package com.muzo.musicapp.feature.fragment.section2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
import com.muzo.musicapp.core.data.local.repository.LocalMainRepository
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.core.data.remote.repository.MusicRepository
import com.muzo.musicapp.domain.usecase.GetHomeMusicUseCase
import com.muzo.musicapp.feature.viewmodel.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SectionsViewModel @Inject constructor(
    private val getHomeMusicUseCase: GetHomeMusicUseCase,
    private val localMusicRepository: LocalMainRepository,
    private val localMusicDataSource: LocalMusicDataSource
)  :ViewModel() {

    val _uiState :MutableStateFlow<SectionsUiState> = MutableStateFlow(SectionsUiState())




    init {
        getMusicLocalOrRemote()
    }
    private fun getMusicLocalOrRemote() {
        viewModelScope.launch {
            val localMusicList = localMusicDataSource.getMusicList()

            if (localMusicList.isNotEmpty()) {

                _uiState.value = _uiState.value.copy(loading = false, musicListLocal = localMusicList)
            } else {
                getRemoteMusic()
            }
        }
    }

    private fun getRemoteMusic() {
        viewModelScope.launch {
            getHomeMusicUseCase().asReSource().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = false)
                    }

                    is Resource.Success -> {
                        _uiState.value = _uiState.value.copy(loading = false, musicList = result.data)

                        val musicLocalDataList = convertToMusicLocalDataList(result.data)

                        localMusicRepository.saveMusicList(musicLocalDataList)


                    }
                }
            }.launchIn(this)
        }
    }

    private fun convertToMusicLocalDataList(responseApi: ResponseApi): List<MusicLocalData> {
        return responseApi.results.map { music ->
            MusicLocalData(
                uid = 0, // Otomatik oluşturulan bir uid değeri atanacak
                artistName = music.artistName,
                trackName = music.trackName,
                releaseDate=music.releaseDate,
                trackPrice=music.trackPrice.toString(),
                artworkUrl100=music.artworkUrl100
            )
        }
    }

    fun deleteRoom(musicId: Int) {
        viewModelScope.launch {
            localMusicRepository.deleteMusicById(musicId)
            val updatedList = _uiState.value.musicListLocal?.toMutableList()
            updatedList?.removeAll { it.uid == musicId }
            _uiState.value = _uiState.value.copy(musicListLocal = updatedList)
        }
    }


}

data class SectionsUiState(
    val loading: Boolean = false,
    val musicList: ResponseApi? =null,
    val musicListLocal: List<MusicLocalData>? =null
)
