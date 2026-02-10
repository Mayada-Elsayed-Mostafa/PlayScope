package com.mayada.playscope.data.remote.mapper

import com.mayada.playscope.data.remote.dto.GameDetailsDto
import com.mayada.playscope.domain.model.GameDetails

fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        id = id,
        name = name,
        description = description,
        released = released,
        rating = rating,
        image = image
    )
}
