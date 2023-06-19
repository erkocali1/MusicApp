package com.muzo.musicapp.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.musicapp.core.common.Resource
import com.muzo.musicapp.core.common.asReSource
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
class HomeViewModel @Inject constructor(
    private val getHomeMusicUseCase: GetHomeMusicUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())

    init {
        getMusic()
    }

    private fun getMusic() {
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
}


data class HomeUiState(
    val loading: Boolean = false,
    val musicList: List<ResponseApi> = listOf()
)