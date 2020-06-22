package com.ajaymourya.movieapp.model

import java.lang.Exception

sealed class MovieGenreResult {
    data class Success(val data: List<Movie>) : MovieGenreResult()
    data class Error(val error: Exception) : MovieGenreResult()
}