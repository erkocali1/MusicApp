package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import javax.inject.Inject

interface LocalMainRepository {



      suspend fun saveMusicList(musicList: List<MusicLocalData>)

      suspend fun deleteMusicById(musicId: Int)


}