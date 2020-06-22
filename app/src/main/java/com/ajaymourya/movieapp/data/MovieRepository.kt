package com.ajaymourya.movieapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that works with local and remote data sources.
 */
class MovieRepository(private val service: MovieService) {

    /**
     * get movies by genreId, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getGenreResultStream(genreId: Int): LiveData<PagingData<Movie>> {
        Log.d("MovieRepository", "New genre id: $genreId")
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(service, genreId) }
        ).liveData
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
