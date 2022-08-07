package com.example.gobus.base

import android.os.Bundle
import android.os.PersistableBundle
import dagger.android.support.DaggerAppCompatActivity

class BaseActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
}