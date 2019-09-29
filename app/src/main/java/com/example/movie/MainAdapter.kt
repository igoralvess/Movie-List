package com.example.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.main.data.model.Movie
import com.example.movie.main.data.model.MovieResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*


class MainAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.title.text = movies[position].title
        val poster = holder.view.imageView_poster
        Picasso.with(poster.context).load("https://image.tmdb.org/t/p/w500" + movies[position].poster_path).into(poster)
    }

}