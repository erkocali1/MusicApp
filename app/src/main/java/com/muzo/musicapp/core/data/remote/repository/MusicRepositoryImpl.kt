package com.muzo.musicapp.core.data.remote.repository

import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.core.data.remote.source.MusicRemoteDataSource
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicRemoteDataSource: MusicRemoteDataSource
):MusicRepository {
    override suspend fun result(): Result<ResponseApi> {
        return musicRemoteDataSource.result()
    }
}