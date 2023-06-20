package com.muzo.musicapp.core.data.remote.source

import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.core.data.remote.api.ResultService
import javax.inject.Inject

class MusicRemoteDataSourceImpl @Inject constructor(
    private val resultService: ResultService):MusicRemoteDataSource {
    override suspend fun result(): Result<ResponseApi> {
        return kotlin.runCatching {
            resultService.result()
        }
    }

}