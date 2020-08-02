package com.ajaymourya.movieapp.model

// A generic class that contains data and status about loading this data.
sealed class NetworkStatus<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkStatus<T>()
    object Loading : NetworkStatus<Nothing>()
    data class Error(val exception: Exception) : NetworkStatus<Nothing>()
}