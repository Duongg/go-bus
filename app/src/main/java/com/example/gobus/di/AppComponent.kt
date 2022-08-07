package com.example.gobus.di

import com.example.gobus.viewmodel.BaseViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(baseViewModel: BaseViewModel)

}