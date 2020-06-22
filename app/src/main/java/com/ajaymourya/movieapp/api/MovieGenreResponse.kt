package com.ajaymourya.movieapp.api

import com.ajaymourya.movieapp.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>
)