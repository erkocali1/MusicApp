package com.muzo.musicapp.core.data.remote.repository

import com.muzo.musicapp.core.data.model.ResponseApi

interface MusicRepository {
    suspend fun result():Result<List<ResponseApi>>
}