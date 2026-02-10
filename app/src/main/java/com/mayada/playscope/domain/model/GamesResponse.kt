package com.mayada.playscope.domain.model

data class GamesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameItem>
)