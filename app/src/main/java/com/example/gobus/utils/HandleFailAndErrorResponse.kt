package com.example.gobus.utils

import android.app.Activity
import com.example.gobus.viewstate.FailCase
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HandleFailAndErrorResponse {
    companion object{
        fun handleFail(activity: Activity, failCase: FailCase?, function:(() -> Unit)? = null){
            failCase?.let {
                it.failCode?.let {
                    when{
                        it.equals(500)->{
                            CustomDialog.showDialogNoInternetConnection(
                                activity,
                                "500 error",
                                "Please try again",
                                "OK"
                            )
                        }
                        it.equals(401)->{
                            CustomDialog.showDialogNoInternetConnection(
                                activity,
                                "Unauthorized",
                                "Please try again",
                                "OK"
                            )
                        }
                    }
                }
            }
        }
        fun handleError(activity: Activity, throwable: Throwable?, function:(() -> Unit)? = null){
            throwable?.let {
                when(it){
                    is UnknownHostException ->{
                        CustomDialog.showDialogNoInternetConnection(
                            activity,
                            "No internet connection",
                            "Please make sure that your device is connected to Internet",
                            "OK"
                        )
                    }
                    is SocketException ->{
                        CustomDialog.showDialogNoInternetConnection(
                            activity,
                            "Network is unreachable",
                            "Please try again",
                            "OK"
                        )
                    }
                    is SocketTimeoutException->{
                        CustomDialog.showDialogNoInternetConnection(
                            activity,
                            "Connection time out",
                            "Please try again",
                            "OK"
                        )
                    }
                    else->{
                        CustomDialog.showDialogNoInternetConnection(
                            activity,
                            "An error occurred",
                            "Please try again",
                            "OK"
                        )
                    }
                }

            }
        }
    }
}