package com.muzo.musicapp.core.data.remote.api

import com.muzo.musicapp.core.data.model.ResponseApi
import retrofit2.http.GET


interface ResultService {

    @GET("search?term=jack+johnson&limit=N&offset=X")
    suspend fun result():ResponseApi

}