package com.muzo.musicapp.core.data.local.source

import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import com.muzo.musicapp.core.data.model.Music
import kotlinx.coroutines.flow.Flow

interface LocalMusicDataSource {

    suspend fun getMusicList(): Flow<List<MusicLocalData>>

    suspend fun insertMusic(musicList:List<MusicLocalData>)

    suspend fun deleteMusicByUid(musicId:Int)

    suspend fun getLastClickedMusic():Flow<List<LastClikedMusic>>

    suspend fun insertLastClickedMusic(lastClickedMusic:List<LastClikedMusic>)

    suspend fun getFavMusic():Flow<List<FavLocalData>>

    suspend fun insertFavMusic(favMusic:List<FavLocalData>)

    suspend fun deleteFavMusicByTrackName(trackName:String)

    suspend fun deleteAllFav()




}