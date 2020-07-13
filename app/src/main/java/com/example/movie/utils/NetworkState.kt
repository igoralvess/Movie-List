package com.example.movie.utils

data class NetworkState (
    val status: Status,
    val msg: String
) {
    companion object{
        val LOADED = NetworkState(Status.SUCCESS,"SUCCESS")
        val LOADING = NetworkState(Status.RUNNING,"LOADING")
    }
}

enum class Status{ RUNNING, SUCCESS, FAILED }