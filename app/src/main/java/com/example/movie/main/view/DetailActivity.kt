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
import com.example.movie.main.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setValues()
    }

    private fun setValues() {
        var movie = intent.getSerializableExtra("movie") as Movie
        detailTitle.text = movie.title
        detailOverview.text = movie.overview
        Picasso.with(detailPoster.context).load("https://image.tmdb.org/t/p/original" + movie.backdrop_path).into(detailPoster)
    }
}