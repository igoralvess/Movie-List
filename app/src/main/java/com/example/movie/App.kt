package com.example.movie

import android.app.Application
import com.example.movie.main.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private lateinit var applicaton: Application

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)

        return appComponent
    }
}