package com.ajaymourya.movieapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.data.MovieRepository
import com.ajaymourya.movieapp.model.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieListViewModel(val repository: MovieRepository) : ViewModel() {

    init {
        //repository.getGenreResultStream(878)
        GlobalScope.launch {
            val response = MovieService.create().getMoviesByGenre(genreId = 878, page = 1)
            Log.e("network", " ${response.results}")
        }
    }

    fun getMoviesByGenre(): LiveData<PagingData<Movie>> {
        return repository.getGenreResultStream(878)
    }
}