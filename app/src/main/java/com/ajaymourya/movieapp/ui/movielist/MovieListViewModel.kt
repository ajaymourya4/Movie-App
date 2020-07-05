package com.ajaymourya.movieapp.ui.movielist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ajaymourya.movieapp.data.MovieRepository
import com.ajaymourya.movieapp.model.Movie
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    init {
        Log.e("View Model", " created")
    }

    fun getMovies(): LiveData<PagingData<Movie>> {
        return repository.getGenreResultStream(878)
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("View Model", " destroyed")
    }
}