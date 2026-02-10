package com.mayada.playscope.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayada.playscope.data.local.dao.GamesDao
import com.mayada.playscope.data.local.entity.GameDetailsEntity
import com.mayada.playscope.data.local.entity.GameEntity

@Database(
    entities = [GameEntity::class, GameDetailsEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}