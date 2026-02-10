package com.mayada.playscope.data.remote

import com.mayada.playscope.data.remote.dto.GameDetailsDto
import com.mayada.playscope.data.remote.dto.GamesResponseDto
import com.mayada.playscope.data.remote.dto.GenresResponseDto
import com.mayada.playscope.domain.model.GameDetails
import com.mayada.playscope.domain.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApiService {

    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("genres") genre: String
    ): GamesResponseDto

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int
    ): GameDetailsDto

    @GET("genres")
    suspend fun getGenres(): GenresResponseDto
}
