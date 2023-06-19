package com.muzo.musicapp.core.data.remote.api

import com.muzo.musicapp.core.data.model.ResponseApi
import retrofit2.http.GET
import retrofit2.http.POST

interface ResultService {

    @GET("search")
    suspend fun result():List<ResponseApi>

}