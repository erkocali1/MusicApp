package com.muzo.musicapp.core.data.remote.api

import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.core.data.model.ResponseApi
import com.muzo.musicapp.core.data.model.ResponsePagination
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ResultService {

    @GET("search?term=jack+johnson&limit=50&offset=250")
    suspend fun result():ResponseApi

    @GET("search")
    suspend fun pagination(
        @Query("term") searchWord: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ):Response<ResponsePagination<PaginationList>>


}