package com.ajaymourya.movieapp.ui.movielist

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ajaymourya.movieapp.R
import com.ajaymourya.movieapp.databinding.MovieListFragmentBinding
import com.ajaymourya.movieapp.di.Injectable
import com.ajaymourya.movieapp.model.Movie
import com.ajaymourya.movieapp.viewmodel.ViewModelFactory
import javax.inject.Inject

class MovieListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: MovieListViewModel

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // To enable toolbar menu item
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        // Need to set when using toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.movieListToolbar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        movieAdapter = MovieAdapter(this::onItemClicked)
        // Handle scroll state on configuration change
        movieAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        binding.moviesList.adapter = movieAdapter
        viewModel.getMovies().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                movieAdapter.submitData(lifecycle, it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.movie_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.theme_change -> {
                toggleTheme()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleTheme() {
        val currentNightMode =
            resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                // Night mode is not active, we're using the light theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                // Night mode is active, we're using dark theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun onItemClicked(movie: Movie) {
        Log.e("onItemClicked", " $movie")
        val movieId = movie.id
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movieId)
        this.findNavController().navigate(action)
    }
}