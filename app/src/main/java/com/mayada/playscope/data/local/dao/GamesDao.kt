package com.mayada.playscope.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mayada.playscope.data.local.entity.GameDetailsEntity
import com.mayada.playscope.data.local.entity.GameEntity

@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Query("SELECT * FROM games WHERE genre = :genre")
    suspend fun getGamesByGenre(genre: String): List<GameEntity>

    @Query("DELETE FROM games WHERE genre = :genre")
    suspend fun deleteGamesByGenre(genre: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameDetails(details: GameDetailsEntity)

    @Query("SELECT * FROM game_details WHERE id = :id")
    suspend fun getGameDetails(id: Int): GameDetailsEntity?
}