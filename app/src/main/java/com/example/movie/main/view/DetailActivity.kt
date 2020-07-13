package com.example.movie.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.R
import com.example.movie.main.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setValues()
    }

    private fun setValues() {
        val df = SimpleDateFormat("dd/MM/yyyy")
        var movie = intent.getSerializableExtra("movie") as Movie
        detailTitle.text = movie.title
        detailOverview.text = movie.overview
        releaseDate.text =  df.format(movie.release_date)
        vote.text = movie.vote_average.toString() + "/10"
        Picasso.with(detailPoster.context).load("https://image.tmdb.org/t/p/original" + movie.backdrop_path).into(detailPoster)
    }
}