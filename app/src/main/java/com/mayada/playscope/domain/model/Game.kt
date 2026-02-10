package com.mayada.playscope.domain.model

import com.google.gson.annotations.SerializedName

data class GameItem(
    val id: Int,
    val name: String,
    @SerializedName("background_image")
    val image: String?,
    val rating: Float?
)

data class GameDetails(
    val id: Int,
    val name: String,
    val description: String,
    val released: String?,
    val rating: Double,
    val image: String?
)
