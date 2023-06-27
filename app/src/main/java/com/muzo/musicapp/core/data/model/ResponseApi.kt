package com.muzo.musicapp.core.data.model


import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val results: List<Music>
)