package com.example.movie.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.repository.MovieDataSource
import com.example.movie.main.data.repository.MovieDataSourceFactory
import com.example.movie.utils.Constants
import com.example.movie.utils.NetworkState
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val movieDataSource: MovieDataSourceFactory
) : ViewModel() {
    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var networkState: LiveData<NetworkState>
    fun init() {
        val config = PagedList.Config.Builder()
            .setPageSize(Constants.PAGE_SIZE)
            .build()
        moviePagedList = LivePagedListBuilder(movieDataSource, config).build()
        val dataSource = movieDataSource.sourceLiveData
        networkState = Transformations.switchMap(dataSource) { movieDataSource ->
            movieDataSource.networkState
        }
    }
    fun refresh() {
        movieDataSource.sourceLiveData.value?.invalidate()
    }
}

