package com.muzo.musicapp.feature.fragment.section2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.domain.usecase.GetHomeMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionTwoViewModel @Inject constructor(
    private val getHomeMusicUseCase: GetHomeMusicUseCase,
    private val localMusicDataSource: LocalMusicDataSource,
) : ViewModel() {
   val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    init {
        getMusic()
    }

     fun getMusic() {
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
                        _uiState.value =
                            _uiState.value.copy(loading = false, musicList = result.data)
                    }
                }
            }.launchIn(this)
        }
    }
    suspend fun saveLastClicked(item:List<LastClikedMusic>){
        localMusicDataSource.insertLastClickedMusic(item)
    }


    fun convertToMusicLocalDataList(music: Music): List<LastClikedMusic> {
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

}

data class HomeUiState(
    val loading: Boolean = false,
    val musicList: ResponseApi? =null
)