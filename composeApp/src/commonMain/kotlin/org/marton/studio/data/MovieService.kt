package org.marton.studio.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MovieService(
    private val apiKey: String,
    private val client: HttpClient
){
    suspend fun getPopularMovies(): MovieResponse {
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey"
        val response = client.get(url).body<String>()
        println("API Response: $response")
        return client.get(url).body<MovieResponse>()
    }
}