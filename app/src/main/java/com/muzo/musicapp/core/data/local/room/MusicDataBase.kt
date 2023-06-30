package com.muzo.musicapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muzo.musicapp.core.data.local.MusicDao.MusicDao


@Database(
    entities = [MusicLocalData::class],
    version = 1
)
abstract class MusicDataBase:RoomDatabase() {


    abstract fun getMusicDao(): MusicDao
}