package com.muzo.musicapp.core.data.remote.source.remotesource

import com.muzo.musicapp.core.data.model.ResponseApi

interface MusicRemoteDataSource {

    suspend fun result():Result<ResponseApi>
}