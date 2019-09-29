package com.example.movie.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.main.adapter.MovieAdapter
import com.example.movie.main.data.model.MovieResponse
import com.example.movie.viewmodel.model.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MovieActivity : AppCompatActivity() {

    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        setupRecycleView()
        viewModel.init()
        viewModel.getRepository().observe(this, Observer<MovieResponse>{ response ->
            adapter.setMovieList(response.results)
        })
    }

    private fun setupRecycleView() {
        adapter = MovieAdapter()
        recyclerView_main.adapter = adapter
        recyclerView_main.layoutManager = GridLayoutManager(this, 3)
    }
}