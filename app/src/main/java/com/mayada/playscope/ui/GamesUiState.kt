package com.mayada.playscope.ui

import com.mayada.playscope.domain.model.GameItem
import com.mayada.playscope.domain.model.Genre

data class GamesUiState(
    val isLoading: Boolean = false,
    val isPaging: Boolean = false,
    val games: List<GameItem> = emptyList(),
    val filteredGames: List<GameItem> = emptyList(),
    val query: String = "",
    val error: String? = null,
    val endReached: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val selectedGenre: Genre? = null,
    val isLoadingGenres: Boolean = false
)
