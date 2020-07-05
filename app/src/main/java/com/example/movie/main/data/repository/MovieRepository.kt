package com.example.movie.main.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.main.data.service.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.movie.main.data.model.MovieRequest
import javax.inject.Inject

interface MovieRepository {
    fun getMovies(params: MovieRequest) : MutableLiveData<MovieResponse>
}
class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override fun getMovies(params: MovieRequest) : MutableLiveData<MovieResponse> {
        val data = MutableLiveData<MovieResponse>()
        this.movieService.getMovies(params.apiKey, params.page).enqueue(object: Callback<MovieResponse?> {
            override fun onResponse(call: Call<MovieResponse?>, response: Response<MovieResponse?>) {
                response.body()?.let {
                    data.value = it
                }
            }
            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}