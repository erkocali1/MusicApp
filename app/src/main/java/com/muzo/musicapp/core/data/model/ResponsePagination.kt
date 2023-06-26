package com.muzo.musicapp.core.data.model

import com.google.gson.annotations.SerializedName


data class ResponsePagination<T>(
    @SerializedName("results")
    val results: List<T>
)