package com.ajaymourya.movieapp.ui.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajaymourya.movieapp.R
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.data.MovieRepository
import com.ajaymourya.movieapp.databinding.MovieListFragmentBinding
import com.ajaymourya.movieapp.ui.ViewModelFactory

class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel
    private lateinit var binding: MovieListFragmentBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(MovieRepository(MovieService.create()))).get(
                MovieListViewModel::class.java
            )
        movieAdapter = MovieAdapter()
        binding.moviesList.adapter = movieAdapter
        viewModel.getMovies().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                movieAdapter.submitData(lifecycle, it)
            }
        })
    }
}