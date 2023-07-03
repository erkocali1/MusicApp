package com.muzo.musicapp.core.data.local.MusicDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muzo.musicapp.core.data.local.room.MusicLocalData

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music: List<MusicLocalData>)

    @Query("SELECT * FROM musicLocalData")
    suspend fun getAllMusic():List<MusicLocalData>

    @Query("DELETE FROM musicLocalData WHERE uid = :musicId")
    suspend fun deleteMusicByUid(musicId: Int)

}