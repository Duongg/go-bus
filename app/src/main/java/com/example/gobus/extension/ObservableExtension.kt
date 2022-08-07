package com.example.gobus.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


fun <T> Observable<T>.observeAndSubcribeOn(): Observable<T>{
    return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, onChanged: Observer<in T>){
    if(!this.hasActiveObservers()){
        this.observe(lifecycleOwner, onChanged)
    }
}