package com.muzo.musicapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic


@Database(
    entities = [MusicLocalData::class,LastClikedMusic::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDataBase:RoomDatabase() {

    abstract fun getMusicDao(): MusicDao
}