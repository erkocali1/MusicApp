package com.muzo.musicapp.domain.usecase


import com.muzo.musicapp.core.data.local.repository.LocalMainRepository
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetDataFromRoomUseCase @Inject constructor(
    private val roomRepository: LocalMainRepository){

    operator fun invoke():Flow<List<MusicLocalData>>{
        return flow {
            val data =roomRepository.getAllDataFromRoom()
            emitAll(data)
        }
    }
}


