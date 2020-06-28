package com.ajaymourya.movieapp.di

import android.app.Application
import com.ajaymourya.movieapp.MovieApplication
import com.ajaymourya.movieapp.ui.movielist.MovieListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, FragmentBuildersModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(movieApplication: MovieApplication)
}