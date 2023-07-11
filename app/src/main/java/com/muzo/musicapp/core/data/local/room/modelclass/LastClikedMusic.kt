package com.muzo.musicapp.core.data.local.room.modelclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "last_clicked_music")
data class  LastClikedMusic(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "artistName")  val  artistName: String,
    @ColumnInfo(name = "trackName")  val trackName: String,
    @ColumnInfo(name = "releaseDate")  val releaseDate: String,
    @ColumnInfo(name = "trackPrice")  val trackPrice: String,
    @ColumnInfo(name = "artworkUrl100")  val artworkUrl100: String,
    @ColumnInfo(name = "collectionName")  val collectionName: String,
    @ColumnInfo(name = "previewUrl")  val previewUrl: String,
) {
}