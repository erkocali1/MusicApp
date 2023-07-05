package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.room.MusicLocalData
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMainRepositoryImpl @Inject constructor(
    private val musicDataSource: LocalMusicDataSource
):LocalMainRepository {
    override suspend fun saveMusicList(musicList: List<MusicLocalData>) {
        return musicDataSource.insertMusic(musicList)
    }

    override suspend fun deleteMusicById(musicId: Int) {
        return musicDataSource.deleteMusicByUid(musicId)
    }

    override suspend fun getAllDataFromRoom(): Flow<List<MusicLocalData>> {
        return musicDataSource.getMusicList()
    }


}