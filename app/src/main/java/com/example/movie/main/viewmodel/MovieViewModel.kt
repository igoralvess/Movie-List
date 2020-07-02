package com.example.movie.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieRequest
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.main.data.repository.MovieRepository
import com.example.movie.utils.Constants

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

      private lateinit var mutableLiveData: MutableLiveData<MovieResponse>

    fun init() {
        val params = MovieRequest(Constants.API_KEY, "1")
        mutableLiveData = repository.getMovies(params)
    }

    fun getMovies(): MutableLiveData<MovieResponse> {
        return mutableLiveData
    }

    class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }

    }
}