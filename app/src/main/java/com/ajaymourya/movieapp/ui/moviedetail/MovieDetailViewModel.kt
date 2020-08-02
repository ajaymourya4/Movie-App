package com.ajaymourya.movieapp.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ajaymourya.movieapp.data.MovieRepository
import com.ajaymourya.movieapp.model.MovieDetail
import com.ajaymourya.movieapp.model.NetworkStatus
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val repository: MovieRepository) :
    ViewModel() {

    fun getMovieDetailById(movieId: Int): LiveData<NetworkStatus<MovieDetail>> {
        return repository.getMovieDetail(movieId)
    }
}