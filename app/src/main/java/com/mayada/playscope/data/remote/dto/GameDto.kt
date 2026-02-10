package com.mayada.playscope.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Int,
    val name: String,
    val rating: Float,
    @SerializedName("background_image")
    val imageUrl: String?
)
