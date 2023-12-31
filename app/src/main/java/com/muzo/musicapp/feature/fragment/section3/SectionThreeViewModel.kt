package com.muzo.musicapp.feature.fragment.section3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.domain.usecase.GetLastClickedFromRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SectionThreeViewModel @Inject constructor(
    private val getLastClickedFromRoomUseCase: GetLastClickedFromRoomUseCase,
    private val  localMusicDataSource: LocalMusicDataSource
) : ViewModel() {

    val _uiState: MutableStateFlow<SectionsUiState> = MutableStateFlow(SectionsUiState())


    init {
        viewModelScope.launch {
            getLastMusic()
        }
    }


    private fun getLastMusic() {
        viewModelScope.launch {
            getLastClickedFromRoomUseCase().asReSource().onEach { result ->

                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = false)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            _uiState.value.copy(loading = false, musicListLocal = result.data)
                    }
                }
            }.launchIn(this)

        }


    }

    suspend fun deleteAll(){
        localMusicDataSource.deleteAllFav()
    }


}

data class SectionsUiState(
    val loading: Boolean = false,
    val musicListLocal: List<LastClikedMusic>? = null,
)
