package com.example.movie.main.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movie.main.data.model.Movie
import javax.inject.Inject

class MovieDataSourceFactory @Inject constructor(
    private val repository: MovieRepository
) : DataSource.Factory<Int, Movie>() {
    private val movieLiveDataSource = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Movie> {
        val userDataSource = MovieDataSource(repository)
        movieLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}