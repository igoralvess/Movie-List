package com.example.movie.main.di

import android.app.Application
import com.example.movie.App
import com.example.movie.main.di.modules.ActivityModule
import com.example.movie.main.di.modules.ApplicationModule
import com.example.movie.main.di.modules.NetworkModule
import com.example.movie.main.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = ([
        ActivityModule::class,
        AndroidInjectionModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ApplicationModule::class
    ])
)

interface ApplicationComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}