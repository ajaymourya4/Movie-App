package com.ajaymourya.movieapp.ui.movielist

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.data.MovieRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        //repository.getGenreResultStream(878)
        GlobalScope.launch {
            val response = MovieService.create().getMoviesByGenre(genreId = 878, page = 1)
            Log.e("network", " ${response.results}")
        }
    }

}