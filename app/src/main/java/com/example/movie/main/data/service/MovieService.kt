package com.example.movie.main.data.service

import com.example.movie.main.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    fun getMovies(@Query("api_key") apiKey : String,
//                 @Query("language") language : String,
//                 @Query("sort_by") sortBy : String,
//                 @Query("include_adult") includeAdult : String,
//                 @Query("include_video") includeVideo : String,
                 @Query("page") page : String) : Call<MovieResponse>
}