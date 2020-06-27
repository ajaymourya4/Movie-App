package com.ajaymourya.movieapp.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajaymourya.movieapp.databinding.MovieListItemBinding
import com.ajaymourya.movieapp.model.Movie

class MovieViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MovieListItemBinding.inflate(layoutInflater, parent, false)
            return MovieViewHolder(binding)
        }
    }
}