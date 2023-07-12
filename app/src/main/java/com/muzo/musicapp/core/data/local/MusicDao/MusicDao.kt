package com.muzo.musicapp.core.data.local.MusicDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertMusic(music: List<MusicLocalData>)

    @Query("SELECT * FROM musicLocalData")
    fun getAllMusic(): Flow<List<MusicLocalData>>

    @Query("DELETE FROM musicLocalData WHERE uid = :musicId")
    suspend fun deleteMusicByUid(musicId: Int)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastClickedMusic(lastClickedMusic:List<LastClikedMusic>)


    @Query("SELECT * FROM last_clicked_music")
    fun getAllLastClickedMusic(): Flow<List<LastClikedMusic>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavMusic(favMusic:List<FavLocalData>)


    @Query("SELECT * FROM fav_music")
    fun getAllFavMusic(): Flow<List<FavLocalData>>


    @Query("DELETE FROM fav_music WHERE trackName = :trackName")
    suspend fun deleteFavMusicByTrackName(trackName: String)




}