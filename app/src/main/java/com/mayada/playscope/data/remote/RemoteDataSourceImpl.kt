package com.mayada.playscope.data.remote

import com.mayada.playscope.data.remote.dto.GameDetailsDto
import com.mayada.playscope.data.remote.dto.GamesResponseDto
import com.mayada.playscope.data.remote.dto.GenresResponseDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: RawgApiService
) : RemoteDataSource {

    override suspend fun getGames(
        genres: String,
        page: Int,
        pageSize: Int
    ): GamesResponseDto {
        return service.getGames(
            page = page,
            pageSize = pageSize,
            genre = genres
        )
    }

    override suspend fun getGameDetails(id: Int): GameDetailsDto {
        return service.getGameDetails(id)
    }

    override suspend fun getGenres(): GenresResponseDto {
        return service.getGenres()
    }

}
