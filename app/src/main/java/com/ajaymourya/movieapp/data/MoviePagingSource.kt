package com.ajaymourya.movieapp.data

import android.util.Log
import androidx.paging.PagingSource
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.model.Movie
import retrofit2.HttpException
import java.io.IOException

private const val MOVIE_STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val service: MovieService,
    private val genreId: Int
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: MOVIE_STARTING_PAGE_INDEX
        return try {
            val response = service.getMoviesByGenre(genreId = genreId, page = params.loadSize)
            val movies = response.results
            LoadResult.Page(
                data = movies,
                prevKey = if (position == MOVIE_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}