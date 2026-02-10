package com.mayada.playscope.di

import android.content.Context
import androidx.room.Room
import com.mayada.playscope.data.local.AppDatabase
import com.mayada.playscope.data.local.dao.GamesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "playscope_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGamesDao(database: AppDatabase): GamesDao {
        return database.gamesDao()
    }
}