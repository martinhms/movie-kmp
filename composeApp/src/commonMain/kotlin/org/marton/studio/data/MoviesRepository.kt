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

fun DetailMovieResponse.toDomainMovie() = Movie(
    id = id,
    title = title,
    poster = if (posterPath != null) "https://image.tmdb.org/t/p/w185/$posterPath" else "",
    overview = overview,
    releaseNote = releaseDate,
    backdrop = backdropPath.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
)


