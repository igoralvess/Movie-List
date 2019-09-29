package com.example.movie

import com.example.movie.main.data.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/discover/movie")
    fun discover(@Query("api_key") api_movie : String,
                 @Query("language") language : String,
                 @Query("sort_by") sort_by : String,
                 @Query("include_adult") include_adult : String,
                 @Query("include_video") include_video : String,
                 @Query("page") page : String
    ) : Call<MovieResponse>
}