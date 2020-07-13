package com.example.movie.main.di.modules

import com.example.movie.BuildConfig
import com.example.movie.main.data.model.MovieRequest
import com.example.movie.main.data.service.MovieService
import com.example.movie.utils.Constants
import com.example.movie.utils.Network
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideService(): MovieService {
        return Network.retrofit.create(MovieService::class.java)
    }
}