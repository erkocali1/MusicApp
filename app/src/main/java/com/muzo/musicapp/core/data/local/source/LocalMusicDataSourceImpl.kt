package com.muzo.musicapp.core.data.local.source

import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMusicDataSourceImpl @Inject constructor(
    private val musicDao: MusicDao
) : LocalMusicDataSource {
    override suspend fun getMusicList(): Flow<List<MusicLocalData>> {
        return musicDao.getAllMusic()
    }

    override suspend fun insertMusic(musicList: List<MusicLocalData>) {
        return musicDao.insertMusic(musicList)
    }

    override suspend fun deleteMusicByUid(musicId: Int) {
       return musicDao.deleteMusicByUid(musicId)
    }

}