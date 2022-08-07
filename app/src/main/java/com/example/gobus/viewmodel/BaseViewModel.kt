package com.example.gobus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gobus.di.MainApplication
import com.example.gobus.repository.ApiRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel: ViewModel() {
    val compositeDisposable = CompositeDisposable()
    init {
        MainApplication.appComponent.inject(this)
    }
    @Inject
    lateinit var apiRepository:  ApiRepository

}