package com.ajaymourya.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajaymourya.movieapp.R
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.data.MovieRepository

class MovieListActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(MovieRepository(MovieService.create()))).get(
                MovieListViewModel::class.java
            )

        viewModel.getMoviesByGenre().observe(this, Observer {
            Log.e("result", " ${it}")
        })

    }
}
