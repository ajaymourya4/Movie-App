package com.ajaymourya.movieapp.di

import com.ajaymourya.movieapp.api.MovieService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMovieService(): MovieService {
        return MovieService.create()
    }
}