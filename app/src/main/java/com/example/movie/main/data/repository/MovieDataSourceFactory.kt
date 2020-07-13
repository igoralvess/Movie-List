package com.example.movie.main.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movie.main.data.model.Movie
import com.example.movie.utils.ApiCallback
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private val repository: MovieRepository
) : DataSource.Factory<Int, Movie>() {
    val sourceLiveData = MutableLiveData<MovieDataSource>()
    private lateinit var movieDataSource: MovieDataSource
    override fun create(): DataSource<Int, Movie> {
        movieDataSource = MovieDataSource(repository)
        sourceLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}