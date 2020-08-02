package com.ajaymourya.movieapp.api

import androidx.lifecycle.LiveData
import com.ajaymourya.movieapp.model.MovieDetail
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * TMDB API communication setup via Retrofit
 */
interface MovieService {

    /**
     * Get movies by particular genre
     */
    @GET("genre/{genre_id}/movies")
    suspend fun getMoviesByGenre(
        @Path("genre_id") genreId: Int,
        @Query("api_key") apiKey: String = "0ce1bcf1a1a069c002a789ddbb67c47b",
        @Query("page") page: Int = 1
    ): MovieGenreResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetailById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "0ce1bcf1a1a069c002a789ddbb67c47b"
    ): Response<MovieDetail>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): MovieService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }
}