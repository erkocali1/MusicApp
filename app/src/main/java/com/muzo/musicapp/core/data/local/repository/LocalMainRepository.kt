package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import kotlinx.coroutines.flow.Flow


interface LocalMainRepository  {



      suspend fun saveMusicList(musicList: List<MusicLocalData>)
      suspend fun deleteMusicById(musicId: Int)

      suspend fun getAllDataFromRoom():Flow<List<MusicLocalData>>

      suspend fun saveLastClickedMusic(lastClikedMusic:List<LastClikedMusic>)

      suspend fun getallLastClickedMusic():Flow<List<LastClikedMusic>>




}