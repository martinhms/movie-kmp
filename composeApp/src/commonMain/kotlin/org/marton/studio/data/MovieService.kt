package org.marton.studio.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val client: HttpClient
) {
    suspend fun getPopularMovies(): MovieResponse {
        val url = "/3/movie/popular"
        return client.get(url).body<MovieResponse>()
    }

    suspend fun fetchMovieDetails(id: String): DetailMovieResponse {
        val url = "/3/movie/$id"
//        println("fetchMovieDetails URL: $url")
//           val response = client.get(url).body<String>()
//            println("fetchMovieDetails API Response: $response")
        return client.get(url).body<DetailMovieResponse>()
    }
}