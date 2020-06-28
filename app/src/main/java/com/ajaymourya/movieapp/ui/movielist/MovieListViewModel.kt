package com.ajaymourya.movieapp.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ajaymourya.movieapp.data.MovieRepository
import com.ajaymourya.movieapp.model.Movie
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    init {

    }

    fun getMovies(): LiveData<PagingData<Movie>> {
        return repository.getGenreResultStream(878)
    }

}