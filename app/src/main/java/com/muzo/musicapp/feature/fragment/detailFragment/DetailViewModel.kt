package com.muzo.musicapp.feature.fragment.detailFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.domain.usecase.GetFavFromRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getFavFromRoomUseCase: GetFavFromRoomUseCase,
    private val localMusicDataSource: LocalMusicDataSource

) : ViewModel() {
    val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    init {
        getMusic()
    }

  private  fun getMusic() {
        viewModelScope.launch {
            getFavFromRoomUseCase().asReSource().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = false)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            _uiState.value.copy(loading = false)
                    }
                }
            }.launchIn(this)
        }
    }

    suspend fun saveFavList(item:List<FavLocalData>){
        localMusicDataSource.insertFavMusic(item)
    }

    suspend  fun deleteFavList(trackName:String){
        localMusicDataSource.deleteFavMusicByTrackName(trackName)
    }

}

data class HomeUiState(
    val loading: Boolean = false,
    )