package com.example.movie.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.main.adapter.MainNavigator
import com.example.movie.main.adapter.MovieAdapter
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.main.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MovieActivity : AppCompatActivity(), MainNavigator {

    lateinit var adapter: MovieAdapter
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecycleView()
        setupObservers()
        setupListeners()
    }

    private fun setupRecycleView() {
        adapter = MovieAdapter()
        recyclerView_main.adapter = adapter
        recyclerView_main.layoutManager = GridLayoutManager(this, 3)
    }

    private fun setupObservers() {
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.init()
        viewModel.getRepository().observe(this, Observer<MovieResponse>{ response ->
            adapter.setMovieList(response.results)
        })
    }

    private fun setupListeners() {
        viewModel.setNavigator(this)
    }

    override fun onItemClick(movie: Movie) {
        startActivity(Intent(this, DetailActivity::class.java))
    }
}