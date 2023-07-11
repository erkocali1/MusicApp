package com.muzo.musicapp.domain.usecase

import com.muzo.musicapp.core.data.local.repository.LocalMainRepository
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class GetLastClickedFromRoomUseCase @Inject constructor(
    private val roomRepository: LocalMainRepository
){

    operator fun invoke(): Flow<List<LastClikedMusic>> {
        return flow {
            val data =roomRepository.getallLastClickedMusic()
            emitAll(data)
        }
    }
}