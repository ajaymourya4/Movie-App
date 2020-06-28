package com.ajaymourya.movieapp

import android.app.Application
import com.ajaymourya.movieapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MovieApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var daggerAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = daggerAndroidInjector
}