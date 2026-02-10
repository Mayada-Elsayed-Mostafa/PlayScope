package com.mayada.playscope.data.remote.mapper

import com.mayada.playscope.data.remote.dto.GenreDto
import com.mayada.playscope.domain.model.Genre

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        slug = slug
    )
}