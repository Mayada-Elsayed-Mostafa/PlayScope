package com.mayada.playscope.domain.model

// Optional enhancements
data class Trailer(val id: Int, val name: String, val data: TrailerData)
data class TrailerData(val max: String)
