package org.marton.studio.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("page") val page: Int? = null,
    @SerialName("results") val results: List<DetailMovieResponse>? = null,
    @SerialName("total_pages") val totalPages: Int? = null,
    @SerialName("total_results") val totalResults: Int? = null
)

@Serializable
data class DetailMovieResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    val adult: Boolean,
    @SerialName("overview") val overview: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("backdrop_path")  val backdropPath: String?,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("vote_average") val voteAverage: Double,
)
