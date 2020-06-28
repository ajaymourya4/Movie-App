package com.ajaymourya.movieapp.di

import com.ajaymourya.movieapp.ui.movielist.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment
}