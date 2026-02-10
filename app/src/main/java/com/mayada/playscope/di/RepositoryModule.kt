package com.mayada.playscope.di

import com.mayada.playscope.data.repo_implementation.GamesRepositoryImpl
import com.mayada.playscope.domain.repo_interfaces.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGamesRepository(
        impl: GamesRepositoryImpl
    ): GamesRepository
}
