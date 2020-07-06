package com.example.movie.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.repository.MovieDataSourceFactory
import com.example.movie.utils.Constants
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieDataSource: MovieDataSourceFactory
) : ViewModel() {
    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    fun init() {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSource, config).build()
    }
}