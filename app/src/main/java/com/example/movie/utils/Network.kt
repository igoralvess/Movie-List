package com.example.movie.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    val retrofit get() = Retrofit.Builder()
        .baseUrl(Constants.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}