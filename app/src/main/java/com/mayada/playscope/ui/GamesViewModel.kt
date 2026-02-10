package com.mayada.playscope.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayada.playscope.domain.model.GameItem
import com.mayada.playscope.domain.model.Genre
import com.mayada.playscope.domain.repo_interfaces.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repository: GamesRepository
) : ViewModel() {

    private var currentPage = 1

    private val _uiState = MutableStateFlow(GamesUiState())
    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    init {
        loadGenres()
    }

    private fun loadGenres() {
        _uiState.update { it.copy(isLoadingGenres = true) }

        viewModelScope.launch {
            runCatching {
                repository.getGenres()
            }.onSuccess { genres ->
                val firstGenre = genres.firstOrNull()
                _uiState.update {
                    it.copy(
                        genres = genres,
                        selectedGenre = firstGenre,
                        isLoadingGenres = false
                    )
                }
                firstGenre?.let { loadGames() }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        isLoadingGenres = false,
                        error = it.error ?: "Failed to load genres"
                    )
                }
            }
        }
    }

    fun loadGames() {
        if (_uiState.value.isLoading) return

        val genre = _uiState.value.selectedGenre?.slug ?: "action"

        _uiState.update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {
            runCatching {
                repository.getGames(page = currentPage, genre = genre)
            }.onSuccess { games ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        games = games,
                        filteredGames = applySearch(games, it.query),
                        endReached = games.isEmpty()
                    )
                }
            }.onFailure { throwable ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = throwable.message ?: "Something went wrong"
                    )
                }
            }
        }
    }

    fun onGenreSelected(genre: Genre) {
        if (_uiState.value.selectedGenre?.id == genre.id) return

        currentPage = 1
        _uiState.update {
            it.copy(
                selectedGenre = genre,
                games = emptyList(),
                filteredGames = emptyList(),
                query = "",
                endReached = false,
                error = null
            )
        }
        loadGames()
    }

    fun loadNextPage() {
        if (_uiState.value.isPaging || _uiState.value.endReached) return

        _uiState.update { it.copy(isPaging = true) }

        val genre = _uiState.value.selectedGenre?.slug ?: "action"

        viewModelScope.launch {
            runCatching {
                repository.getGames(page = currentPage + 1, genre = genre)
            }.onSuccess { newGames ->
                currentPage++
                val allGames = _uiState.value.games + newGames
                _uiState.update {
                    it.copy(
                        isPaging = false,
                        games = allGames,
                        filteredGames = applySearch(allGames, it.query),
                        endReached = newGames.isEmpty()
                    )
                }
            }.onFailure {
                _uiState.update { it.copy(isPaging = false) }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(
                query = query,
                filteredGames = applySearch(it.games, query)
            )
        }
    }

    private fun applySearch(games: List<GameItem>, query: String): List<GameItem> {
        if (query.isBlank()) return games
        return games.filter { it.name.contains(query, ignoreCase = true) }
    }

    fun retry() {
        currentPage = 1
        loadGames()
    }
}