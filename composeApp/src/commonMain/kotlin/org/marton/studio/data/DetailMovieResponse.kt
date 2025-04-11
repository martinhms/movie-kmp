package org.marton.studio.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailMovieResponse(
    val adult: Boolean,
    @SerialName("backdrop_path")  val backdropPath: String,
    val budget: Int,
    @SerialName("genres")
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path") val posterPath: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
)

@Serializable
data class Genre(
    val id: Int,
    val name: String
)
