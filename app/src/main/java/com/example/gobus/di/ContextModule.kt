package com.example.gobus.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val application: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = application
}