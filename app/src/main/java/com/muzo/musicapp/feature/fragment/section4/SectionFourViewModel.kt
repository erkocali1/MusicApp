package com.muzo.musicapp.feature.fragment.section4


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.domain.usecase.GetFavFromRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SectionFourViewModel @Inject constructor(
    private val getFavFromRoomUseCase: GetFavFromRoomUseCase,
    private val localMusicDataSource: LocalMusicDataSource
) : ViewModel() {

    val _uiState: MutableStateFlow<SectionsUiState> = MutableStateFlow(SectionsUiState())


    init {
        viewModelScope.launch {
            getFavList()
        }
    }

    private fun getFavList() {

        viewModelScope.launch {

            getFavFromRoomUseCase().asReSource().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            _uiState.value.copy(loading = false, favMusicList = result.data)
                    }
                }
            }.launchIn(this)

        }

    }

    suspend fun deleteFromRoom(musicId: Int) {
        localMusicDataSource.deleteMusicByUid(musicId)
    }


}

data class SectionsUiState(
    val loading: Boolean = false,
    val favMusicList: List<FavLocalData>? = null,
)
