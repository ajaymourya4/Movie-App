package com.ajaymourya.movieapp.ui.movielist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ajaymourya.movieapp.R
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.data.MovieRepository
import com.ajaymourya.movieapp.ui.ViewModelFactory

class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(MovieRepository(MovieService.create()))).get(
                MovieListViewModel::class.java
            )
    }

}