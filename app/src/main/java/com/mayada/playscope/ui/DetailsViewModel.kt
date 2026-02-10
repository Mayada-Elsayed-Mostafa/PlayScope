package com.mayada.playscope.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayada.playscope.domain.repo_interfaces.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val repository: GamesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val gameId: Int =
        checkNotNull(savedStateHandle["gameId"])

    private val _uiState =
        MutableStateFlow<GameDetailsUiState>(GameDetailsUiState.Loading)
    val uiState: StateFlow<GameDetailsUiState> = _uiState.asStateFlow()

    init {
        loadGameDetails()
    }

    private fun loadGameDetails() {
        viewModelScope.launch {
            runCatching {
                repository.getGameDetails(gameId)
            }.onSuccess { game ->
                _uiState.value = GameDetailsUiState.Success(game)
            }.onFailure { throwable ->
                _uiState.value = GameDetailsUiState.Error(
                    throwable.message ?: "Failed to load game details"
                )
            }
        }
    }
}
