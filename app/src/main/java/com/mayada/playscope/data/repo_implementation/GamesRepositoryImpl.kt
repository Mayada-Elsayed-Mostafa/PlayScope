package com.mayada.playscope.data.repo_implementation

import com.mayada.playscope.data.local.dao.GamesDao
import com.mayada.playscope.data.local.mapper.toDomain
import com.mayada.playscope.data.local.mapper.toEntity
import com.mayada.playscope.data.remote.RemoteDataSource
import com.mayada.playscope.data.remote.mapper.toDomain
import com.mayada.playscope.domain.model.GameDetails
import com.mayada.playscope.domain.model.GameItem
import com.mayada.playscope.domain.model.Genre
import com.mayada.playscope.domain.repo_interfaces.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val gamesDao: GamesDao
) : GamesRepository {

    override suspend fun getGames(
        page: Int,
        pageSize: Int,
        genre: String
    ): List<GameItem> {
        return try {
            val games = remoteDataSource
                .getGames(genre, page, pageSize)
                .results
                .map { it.toDomain() }

            if (page == 1) {
                gamesDao.deleteGamesByGenre(genre)
            }
            gamesDao.insertGames(games.map { it.toEntity(genre) })

            games
        } catch (e: Exception) {
            if (page == 1) {
                gamesDao.getGamesByGenre(genre).map { it.toDomain() }
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getGameDetails(id: Int): GameDetails {
        return try {
            val details = remoteDataSource
                .getGameDetails(id)
                .toDomain()
            gamesDao.insertGameDetails(details.toEntity())
            details
        } catch (e: Exception) {
            gamesDao.getGameDetails(id)?.toDomain()
                ?: throw Exception("No internet connection and no cached data")
        }
    }

    override suspend fun getGenres(): List<Genre> {
        return remoteDataSource.getGenres().results.map { it.toDomain() }
    }
}