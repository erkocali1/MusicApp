package com.muzo.musicapp.core.data.local.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.muzo.musicapp.core.data.local.MusicDao.MusicDao



@Database(
    entities = [MusicLocalData::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDataBase:RoomDatabase() {


    abstract fun getMusicDao(): MusicDao
}