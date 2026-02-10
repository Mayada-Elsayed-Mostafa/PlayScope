# PlayScope

An Android application that allows users to browse video games by genre using the [RAWG API](https://rawg.io/apidocs).

---

## Tech Stack

| Category | Library |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| Async | Kotlin Coroutines + Flow |
| Networking | Retrofit 2 + OkHttp |
| DI | Dagger Hilt |
| Image Loading | Coil 3 |
| Local Database | Room |
| Navigation | Jetpack Navigation Compose |

---

## Architecture

**MVVM (Model - View - ViewModel)**

The project is structured into 4 layers:

- **Domain** – Models and repository interfaces. Pure Kotlin, no Android dependencies.
- **Data** – Repository implementation, remote DTOs, mappers, and local Room entities.
- **DI** – Hilt modules for networking, database, and repository bindings.
- **UI** – Composable screens, ViewModels, and navigation.

MVVM was chosen for its simplicity, clear separation of concerns, and first-class support in Android with Jetpack components.

---

## Features

- Browse games by genre using a scrollable filter chip row
- Pagination: loads next page when scrolling near the end of the list
- Local search: filters already-loaded games in memory without API calls
- Loading, error, and empty states handled on both screens
- Game details screen with name, image, release date, rating, and description
- Offline support: games list and details are cached locally via Room

---

## Screens

### Games List
- Displays game name, image, and rating
- Genre filter chips at the top
- Search bar for local filtering
- Pagination with distinct loading indicators (initial vs. paging)
- Error state with retry button
- Empty state when no results match

### Game Details
- Displays name, image, release date, rating, and description
- Cached locally for offline access

---

## Assumptions & Shortcuts

- Genre list is fetched dynamically from the API on app launch. No hardcoded genres.
- Offline support covers the games list (first page per genre) and game details for previously visited games. Pagination is not available offline.
- No use case / interactor layer was added to keep the structure simple given the scope of the task.

---

## Bonus

- Offline support with Room caching
