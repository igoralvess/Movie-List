package com.example.movie.viewmodel.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie.main.data.model.MovieRequest
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.main.data.repository.MovieRepository
import com.example.movie.utils.Constants

class MovieViewModel : ViewModel() {

//    val showLoading = MutableLiveData<Boolean>()
    private lateinit var mutableLiveData: MutableLiveData<MovieResponse>
    private lateinit var repository: MovieRepository

    fun init() {
        repository = MovieRepository().getInstance()
        val params = MovieRequest(Constants.API_KEY, "1")
        mutableLiveData = repository.getMovies(params)
    }

    fun getRepository(): MutableLiveData<MovieResponse> {
        return mutableLiveData
    }
}