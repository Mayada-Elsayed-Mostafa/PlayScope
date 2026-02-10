package com.mayada.playscope.domain.repo_interfaces

import com.mayada.playscope.domain.model.GameDetails
import com.mayada.playscope.domain.model.GameItem
import com.mayada.playscope.domain.model.Genre

interface GamesRepository {

    suspend fun getGames(
        page: Int,
        pageSize: Int = 20,
        genre: String = "action"
    ): List<GameItem>

    suspend fun getGameDetails(
        id: Int
    ): GameDetails

    suspend fun getGenres(): List<Genre>
}
