package com.mayada.playscope.ui

import com.mayada.playscope.domain.model.GameDetails

sealed class GameDetailsUiState {
    object Loading : GameDetailsUiState()
    data class Success(val game: GameDetails) : GameDetailsUiState()
    data class Error(val message: String) : GameDetailsUiState()
}
