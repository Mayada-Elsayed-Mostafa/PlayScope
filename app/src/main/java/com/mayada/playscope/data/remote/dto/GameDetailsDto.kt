package com.mayada.playscope.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDetailsDto(
    val id: Int,
    val name: String,
    val description: String,
    val released: String?,
    val rating: Double,
    @SerializedName("background_image")
    val image: String?
)
