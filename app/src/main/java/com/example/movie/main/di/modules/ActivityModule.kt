package com.example.movie.main.di.modules

import com.example.movie.main.view.MovieActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesListActivity() : MovieActivity
}