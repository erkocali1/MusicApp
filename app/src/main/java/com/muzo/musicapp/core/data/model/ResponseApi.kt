package com.muzo.musicapp.core.data.model


import com.google.gson.annotations.SerializedName

data class ResponseApi(
    @SerializedName("results")
    val results: List<Music>
)