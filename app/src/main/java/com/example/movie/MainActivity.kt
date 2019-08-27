package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiKey = "3a486fc9e00686c561325c6b8f5efdc1"
        val language = "en-US"
        val sortBy = "popularity.desc"
        val includeAdult = "false"
        val includeVideo = "false"
        val page = "1"


        val call = RetrofitInitializer().movieService().discover(apiKey, language, sortBy, includeAdult, includeVideo, page)
        call.enqueue(object: Callback<MovieResponse?> {
            override fun onResponse(call: Call<MovieResponse?>, response: Response<MovieResponse?>) {
                response?.body()?.let{
                    val movies = it
                    println(movies)
                    configureMovies(movies)
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun configureMovies(movies: MovieResponse) {
        recyclerView_main.layoutManager = GridLayoutManager(this, 3)
        recyclerView_main.adapter = MainAdapter(movies)
    }

}
