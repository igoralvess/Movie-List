package com.example.movie.main.data.repository

import androidx.paging.PageKeyedDataSource
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieRequest
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.utils.ApiCallback
import com.example.movie.utils.Constants
import javax.inject.Inject

class MovieDataSource(
    private val repository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val currentPage = 1
        val params = MovieRequest(Constants.API_KEY, currentPage.toString(), Constants.API_LANG)
        repository.getMovies(params, object : ApiCallback<List<Movie>> {
            override fun onException(error: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(t: List<Movie>) {
                callback.onResult(t, null, currentPage + 1)
            }
        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        val currentPage = params.key
        val params = MovieRequest(Constants.API_KEY, currentPage.toString(), Constants.API_LANG)
        repository.getMovies(params, object : ApiCallback<List<Movie>> {
            override fun onException(error: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onError(error: String) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(t: List<Movie>) {
                callback.onResult(t, currentPage + 1)
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("Not yet implemented")
    }
}