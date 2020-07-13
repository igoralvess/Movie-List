package com.example.movie.main.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.main.data.model.*
import com.example.movie.main.data.service.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.movie.utils.ApiCallback
import javax.inject.Inject

interface MovieRepository {
    fun getMovies(params: MovieRequest, callback: ApiCallback<List<Movie>>)
}

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override fun getMovies(params: MovieRequest, callback: ApiCallback<List<Movie>>) {
        this.movieService.getMovies(params.apiKey, params.page, params.language)
            .enqueue(object : Callback<MovieResponse?> {
                override fun onResponse(
                    call: Call<MovieResponse?>,
                    response: Response<MovieResponse?>
                ) {
                    if (response.isSuccessful)
                        response.body()?.let {
                            callback.onSuccess(it.results)
                        }
                    else callback.onError(response.errorBody().toString())
                }

                override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                    callback.onError("Erro desconhecido")
                }
            })
    }
}