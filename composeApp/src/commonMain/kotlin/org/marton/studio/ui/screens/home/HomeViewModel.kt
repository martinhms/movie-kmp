package org.marton.studio.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.marton.studio.Movie
import org.marton.studio.data.MoviesRepository

class HomeViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            try {
                state = UiState(loading = true)
                val response = repository.fetchPopularMovies()
                state = UiState(loading = false, movies = response)
                println("fetching movies response: $response")
            } catch (e: Exception) {
                println("Error fetching movies ${e.message}")
                state = UiState(loading = false, error = e.message)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: String? = null
    )
}
