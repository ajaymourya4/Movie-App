package com.ajaymourya.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ajaymourya.movieapp.MovieApplication
import com.ajaymourya.movieapp.R
import com.ajaymourya.movieapp.api.MovieService
import com.ajaymourya.movieapp.data.MovieRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
