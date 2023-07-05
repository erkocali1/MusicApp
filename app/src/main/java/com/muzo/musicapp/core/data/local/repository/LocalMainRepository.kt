package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.room.MusicLocalData
import kotlinx.coroutines.flow.Flow


interface LocalMainRepository  {



      suspend fun saveMusicList(musicList: List<MusicLocalData>)
      suspend fun deleteMusicById(musicId: Int)

      suspend fun getAllDataFromRoom():Flow<List<MusicLocalData>>




}