package com.mayada.playscope.data.remote.mapper

import com.mayada.playscope.data.remote.dto.GameDto
import com.mayada.playscope.domain.model.GameItem

fun GameDto.toDomain(): GameItem {
    return GameItem(
        id = id,
        name = name,
        image = imageUrl,
        rating = rating
    )
}
