package com.mayada.playscope.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.mayada.playscope.domain.model.GameItem
import com.mayada.playscope.domain.model.Genre
import com.mayada.playscope.ui.GamesViewModel

@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel(),
    onGameClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = state.query,
            onValueChange = viewModel::onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Search games") },
            singleLine = true
        )

        if (state.genres.isNotEmpty()) {
            GenreFilterRow(
                genres = state.genres,
                selectedGenre = state.selectedGenre,
                onGenreSelected = viewModel::onGenreSelected
            )
        }

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                ErrorScreen(
                    message = state.error,
                    onRetry = viewModel::retry
                )
            }

            state.filteredGames.isEmpty() -> {
                EmptyScreen()
            }

            else -> {
                GamesList(
                    games = state.filteredGames,
                    isPaging = state.isPaging,
                    onLoadMore = viewModel::loadNextPage,
                    onGameClick = onGameClick
                )
            }
        }
    }
}

@Composable
fun GenreFilterRow(
    genres: List<Genre>,
    selectedGenre: Genre?,
    onGenreSelected: (Genre) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        genres.forEach { genre ->
            val isSelected = genre.id == selectedGenre?.id
            FilterChip(
                selected = isSelected,
                onClick = { onGenreSelected(genre) },
                label = { Text(genre.name) }
            )
        }
    }
}

@Composable
fun GamesList(
    games: List<GameItem>,
    isPaging: Boolean,
    onLoadMore: () -> Unit,
    onGameClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        itemsIndexed(games) { index, game ->
            if (index >= games.size - 3) onLoadMore()

            GameItem(game = game, onClick = { onGameClick(game.id) })
        }

        if (isPaging) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun GameItem(game: GameItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row {
            AsyncImage(
                model = game.image,
                contentDescription = game.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "⭐ ${game.rating}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}