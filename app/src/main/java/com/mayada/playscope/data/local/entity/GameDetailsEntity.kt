package com.mayada.playscope.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_details")
data class GameDetailsEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val released: String?,
    val rating: Double,
    val image: String?
)