package com.example.gobus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gobus.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class RouteDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_details)

        toolbar_title.text = "Chi tiết"
        toolbar_button.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}