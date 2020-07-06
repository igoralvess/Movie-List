package com.example.movie.main.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.main.adapter.MovieAdapter
import com.example.movie.main.data.model.Movie
import com.example.movie.main.viewmodel.MovieViewModel
import com.example.movie.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieActivity : DaggerAppCompatActivity() {

    private lateinit  var adapter: MovieAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MovieViewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(MovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel.init()
    }

    private fun setupObservers() {
        viewModel.moviePagedList.observe(this, Observer { response ->
            addMovies(response)
        })
        viewModel
    }

    private fun addMovies(response: PagedList<Movie>) {
        adapter = MovieAdapter {
            startDetail(it)
        }
        adapter.submitList(response)
        recyclerView_main.adapter = adapter
        recyclerView_main.layoutManager = GridLayoutManager(this, 3)
    }

    private fun startDetail(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}