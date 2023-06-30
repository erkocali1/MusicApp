package com.muzo.musicapp.core.data.local.source

import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import javax.inject.Inject

class LocalMusicDataSourceImpl @Inject constructor(
    private val musicDao: MusicDao
) : LocalMusicDataSource {
    override suspend fun getMusicList(): List<MusicLocalData> {
        return musicDao.getAllMusic()
    }
}