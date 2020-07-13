package com.example.movie.utils

interface ApiCallback<T> {
    fun onError(error: String)

    fun onSuccess(t: T)
}
