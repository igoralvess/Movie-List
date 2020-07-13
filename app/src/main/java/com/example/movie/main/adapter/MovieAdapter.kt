package com.example.movie.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.main.data.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter(
    private val onClick: (Movie) -> Unit
    ) : PagedListAdapter<Movie, CustomViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_row, parent, false)
        return CustomViewHolder(cellForRow, onClick)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let{ holder.bind(it) }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                newItem == oldItem
        }
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