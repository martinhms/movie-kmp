package org.marton.studio.data

import org.marton.studio.Movie

class MoviesRepository(private val moviesService: MovieService) {
    suspend fun fetchPopularMovies(): List<Movie> {
        return moviesService.getPopularMovies().results?.map { it.toDomainMovie() } ?: emptyList()
    }

    suspend fun fetchMovieDetails(id: String): Movie {
        return moviesService.fetchMovieDetails(id).toDomainMovie()
    }

}

fun Result.toDomainMovie() = Movie(
    id = id,
    title = title,
    poster =if (posterPath != null) "https://image.tmdb.org/t/p/w500/$posterPath" else "",
)

fun DetailMovieResponse.toDomainMovie() = Movie(
    id = id,
    title = title,
    poster =if (posterPath != null) "https://image.tmdb.org/t/p/w500/$posterPath" else "",
)

