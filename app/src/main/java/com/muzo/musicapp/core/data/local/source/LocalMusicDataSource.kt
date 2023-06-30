package com.muzo.musicapp.core.data.local.source

import com.muzo.musicapp.core.data.local.room.MusicLocalData

interface LocalMusicDataSource {
    suspend fun getMusicList(): List<MusicLocalData>
}