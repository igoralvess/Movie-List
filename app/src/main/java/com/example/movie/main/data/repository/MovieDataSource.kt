package com.example.movie.main.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.movie.BuildConfig
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieRequest
import com.example.movie.utils.ApiCallback
import com.example.movie.utils.Constants
import com.example.movie.utils.NetworkState
import com.example.movie.utils.Status

class MovieDataSource(
    private val repository: MovieRepository
) : PageKeyedDataSource<Int, Movie>() {
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        val currentPage = 1
        val apiParams = MovieRequest(BuildConfig.API_KEY, currentPage.toString(), Constants.API_LANG)
        networkState.postValue(NetworkState.LOADING)
        repository.getMovies(apiParams, object : ApiCallback<List<Movie>> {

            override fun onError(error: String) {
                networkState.postValue(NetworkState(Status.FAILED, error))
                return
            }

            override fun onSuccess(t: List<Movie>) {
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(t, null, currentPage + 1)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        val currentPage = params.key
        val apiParams = MovieRequest(BuildConfig.API_KEY, currentPage.toString(), Constants.API_LANG)
        repository.getMovies(apiParams, object : ApiCallback<List<Movie>> {

            override fun onError(error: String) {
                networkState.postValue(NetworkState(Status.FAILED, error))
                return
            }

            override fun onSuccess(t: List<Movie>) {
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(t, currentPage + 1)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }
}