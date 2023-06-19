package com.muzo.musicapp.core.data.remote.source

import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.core.data.model.ResponseApi

interface MusicRemoteDataSource {

    suspend fun result():Result<List<ResponseApi>>
}