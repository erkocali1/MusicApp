package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import javax.inject.Inject

class LocalMainRepositoryImpl @Inject constructor(
    private val musicDao: MusicDao
):LocalMainRepository {
    override suspend fun saveMusicList(musicList: List<MusicLocalData>) {
        return musicDao.insertMusic(musicList)
    }

    override suspend fun deleteMusicById(musicId: Int) {
        return musicDao.deleteMusicByUid(musicId)
    }


}