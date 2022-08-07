package com.example.gobus.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.gobus.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)
        setListener()
    }

    private fun setListener(){
        val handler = Handler()
        handler.postDelayed({startActivity(Intent(this, MainActivity::class.java)) }, 5000)
    }
}