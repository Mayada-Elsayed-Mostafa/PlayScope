package com.mayada.playscope.data.local.mapper

import com.mayada.playscope.data.local.entity.GameDetailsEntity
import com.mayada.playscope.data.local.entity.GameEntity
import com.mayada.playscope.domain.model.GameDetails
import com.mayada.playscope.domain.model.GameItem

fun GameEntity.toDomain(): GameItem {
    return GameItem(
        id = id,
        name = name,
        image = image,
        rating = rating
    )
}

fun GameItem.toEntity(genre: String): GameEntity {
    return GameEntity(
        id = id,
        name = name,
        image = image,
        rating = rating,
        genre = genre
    )
}

fun GameDetailsEntity.toDomain(): GameDetails {
    return GameDetails(
        id = id,
        name = name,
        description = description,
        released = released,
        rating = rating,
        image = image
    )
}

fun GameDetails.toEntity(): GameDetailsEntity {
    return GameDetailsEntity(
        id = id,
        name = name,
        description = description,
        released = released,
        rating = rating,
        image = image
    )
}
