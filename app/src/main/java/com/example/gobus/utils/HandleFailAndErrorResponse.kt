package com.example.gobus.utils

import android.app.Activity
import com.example.gobus.viewstate.FailCase

class HandleFailAndErrorResponse {
    companion object{
        fun handleFail(activity: Activity, failCase: FailCase?, function:(() -> Unit)? = null){

        }
        fun handleError(activity: Activity, throwable: Throwable?, function:(() -> Unit)? = null){

        }
    }
}