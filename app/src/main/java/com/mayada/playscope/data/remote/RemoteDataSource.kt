package com.mayada.playscope.data.remote

import com.mayada.playscope.data.remote.dto.GameDetailsDto
import com.mayada.playscope.data.remote.dto.GamesResponseDto
import com.mayada.playscope.data.remote.dto.GenresResponseDto

interface RemoteDataSource {

    suspend fun getGames(
        genres: String,
        page: Int,
        pageSize: Int
    ): GamesResponseDto

    suspend fun getGameDetails(
        id: Int
    ): GameDetailsDto

    suspend fun getGenres(): GenresResponseDto



}
