package com.example.movie.main.di.modules

import com.example.movie.main.data.repository.MovieRepository
import com.example.movie.main.data.repository.MovieRepositoryImpl
import com.example.movie.main.data.service.MovieService
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideRepository(movieService: MovieService) : MovieRepository {
        return MovieRepositoryImpl(movieService)
    }
}