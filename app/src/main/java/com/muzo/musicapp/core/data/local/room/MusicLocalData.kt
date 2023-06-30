package com.muzo.musicapp.core.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class  MusicLocalData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "artistName")  val  artistName: String,
    @ColumnInfo(name = "trackName")  val trackName: String,
) {
}