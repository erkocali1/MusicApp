package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.room.MusicLocalData


interface LocalMainRepository  {



      suspend fun saveMusicList(musicList: List<MusicLocalData>)
      suspend fun deleteMusicById(musicId: Int)

      suspend fun getAllDataFromRoom():List<MusicLocalData>




}