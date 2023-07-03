package com.muzo.musicapp.core.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class  MusicLocalData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "artistName")  val  artistName: String,
    @ColumnInfo(name = "trackName")  val trackName: String,
    @ColumnInfo(name = "releaseDate")  val releaseDate: String,
    @ColumnInfo(name = "trackPrice")  val trackPrice: String,
    @ColumnInfo(name = "artworkUrl100")  val artworkUrl100: String,
) {
}