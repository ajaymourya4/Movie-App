package com.ajaymourya.movieapp.ui.movielist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ajaymourya.movieapp.model.Movie
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("loadMovieImage")
fun ImageView.loadMovieImage(movie: Movie) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(this)
}