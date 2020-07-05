package com.example.movie.main.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.main.data.repository.MovieRepository
import com.example.movie.main.view.MovieActivity
import com.example.movie.main.viewmodel.MovieViewModel
import com.example.movie.utils.ViewModelFactory
import com.example.movie.utils.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun provideMovieViewModule(repository: MovieRepository) : ViewModel {
        return MovieViewModel(repository)
    }

    @Provides
    fun provideViewModelFactory(map: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }

}