package com.ajaymourya.movieapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.model.Movie
import com.ajaymourya.movieapp.model.MovieDetail
import com.ajaymourya.movieapp.model.NetworkStatus
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

/**
 * Repository class that works with local and remote data sources.
 */
class MovieRepository @Inject constructor(private val service: MovieService) {

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

    fun getMovieDetail(movieId: Int): LiveData<NetworkStatus<MovieDetail>> {
        val result = MutableLiveData<NetworkStatus<MovieDetail>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                result.postValue(NetworkStatus.Loading)
                val response = service.getMovieDetailById(movieId = movieId)
                result.postValue(NetworkStatus.Success(data = response.body()!!))
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(NetworkStatus.Error(exception = e))
            }
        }
        return result
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }
}
