package com.example.mekotlin58.data.remote.models

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)