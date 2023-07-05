package com.muzo.musicapp.core.data.local.source

import com.muzo.musicapp.core.data.local.room.MusicLocalData
import kotlinx.coroutines.flow.Flow

interface LocalMusicDataSource {
    suspend fun getMusicList(): Flow<List<MusicLocalData>>

    suspend fun insertMusic(musicList:List<MusicLocalData>)

    suspend fun deleteMusicByUid(musicId:Int)

}