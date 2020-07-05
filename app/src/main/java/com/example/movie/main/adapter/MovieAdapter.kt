package com.example.movie.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.main.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter(
    private val movies: ArrayList<Movie>,
    private val onClick: (Movie) -> Unit
    ) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)
        return CustomViewHolder(cellForRow, onClick)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovieList(movies: ArrayList<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}
class CustomViewHolder(private val view : View, private val onClick: (Movie) -> Unit) : RecyclerView.ViewHolder(view) {
    fun bind(movie: Movie) {
        itemView.apply {
            title.text = movie.title
            val poster = imageView_poster
            Picasso.with(poster.context).load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(poster)
            movieCard.setOnClickListener {
                onClick(movie)
            }
        }
    }
}