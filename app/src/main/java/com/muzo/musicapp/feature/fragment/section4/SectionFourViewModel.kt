package com.muzo.musicapp.feature.fragment.section4



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.domain.usecase.GetDataFromRoomUseCase
import com.muzo.musicapp.domain.usecase.GetHomeMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SectionFourViewModel @Inject constructor(
    private val getHomeMusicUseCase: GetHomeMusicUseCase,
    private val localMusicDataSource: LocalMusicDataSource,
    private val getDataFromRoomUseCase: GetDataFromRoomUseCase,
) : ViewModel() {

    val _uiState: MutableStateFlow<SectionsUiState> = MutableStateFlow(SectionsUiState())


    init {
        viewModelScope.launch {
            musicLocalOrRemote()
        }
    }

    private fun musicLocalOrRemote() {
        viewModelScope.launch {

            val localMusicList = getDataFromRoomUseCase.invoke().firstOrNull()

            if (!localMusicList.isNullOrEmpty()) {

                _uiState.value =
                    _uiState.value.copy(loading = false, musicListLocal = localMusicList)

            } else {
                favMusic()
            }
        }

    }


    private fun favMusic() {



    }



    fun deleteRoom(musicId: Int) {
}
}
data class SectionsUiState(
    val loading: Boolean = false,
    val musicList: ResponseApi? = null,
    val musicListLocal: List<MusicLocalData>? = null,
)
