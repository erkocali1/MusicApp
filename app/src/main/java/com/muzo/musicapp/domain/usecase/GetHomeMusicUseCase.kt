package com.muzo.musicapp.domain.usecase

import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.core.data.remote.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeMusicUseCase @Inject constructor(
    private val musicRepository: MusicRepository
) {
    operator fun invoke(): Flow <List<ResponseApi>> {
        return flow {
            val result = musicRepository.result()
            (result.getOrNull() ?: throw IllegalArgumentException("error message")).also {
                emit(it)
            }
        }
    }
}