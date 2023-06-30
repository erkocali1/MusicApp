package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import javax.inject.Inject

interface LocalMainRepository {


//    suspend fun insertMusic(musicDataBase: MusicLocalData) = musicDao.insertMusic(musicDataBase)
//
//
//    suspend fun getAllMusic() = musicDao.getAllMusic()

      suspend fun saveMusicList(musicList: List<MusicLocalData>)

}