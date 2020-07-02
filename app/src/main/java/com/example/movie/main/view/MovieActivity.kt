package com.example.movie.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.main.adapter.MovieAdapter
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.main.data.repository.MovieRepository
import com.example.movie.main.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MovieActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            MovieViewModel.MovieViewModelFactory(MovieRepository().getInstance())
        ).get(MovieViewModel::class.java)
        viewModel.init()
    }

    private fun setupObservers() {
        viewModel.getMovies().observe(this, Observer<MovieResponse> { response ->
            addMovie(response)

        })
    }

    private fun addMovie(response: MovieResponse) {
        adapter = MovieAdapter(response.results) {
            startDetail(it)
        }
        recyclerView_main.adapter = adapter
        recyclerView_main.layoutManager = GridLayoutManager(this, 3)
    }

    private fun startDetail(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}