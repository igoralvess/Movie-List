package com.example.movie.main.data.service

import com.example.movie.main.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: String,
        @Query("language") language : String
    ): Call<MovieResponse>
}
