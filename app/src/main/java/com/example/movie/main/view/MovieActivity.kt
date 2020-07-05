package com.example.movie.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.main.adapter.MovieAdapter
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.main.data.repository.MovieRepository
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
        setupListeners()
    }

    private fun setupListeners() {
        recyclerView_main.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

        })
    }

    private fun setupViewModel() {
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