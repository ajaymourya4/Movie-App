package com.ajaymourya.movieapp.ui.moviedetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ajaymourya.movieapp.R
import com.ajaymourya.movieapp.databinding.MovieDetailFragmentBinding
import com.ajaymourya.movieapp.di.Injectable
import com.ajaymourya.movieapp.model.MovieDetail
import com.ajaymourya.movieapp.model.NetworkStatus
import com.ajaymourya.movieapp.ui.movielist.loadMovieImage
import com.ajaymourya.movieapp.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import javax.inject.Inject

class MovieDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = MovieDetailFragmentArgs.fromBundle(requireArguments()).movieId
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        binding.movieDetailToolbar.setNavigationOnClickListener {
            this.findNavController().popBackStack()
        }

        viewModel.getMovieDetailById(movieId).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it is NetworkStatus.Success)
                    initViews(it.data)
            }
        })
    }

    private fun initViews(movie: MovieDetail) {
        Glide.with(this.requireContext())
            .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(binding.moviePoster)
        binding.movieOverview.text = movie.overview
    }
}