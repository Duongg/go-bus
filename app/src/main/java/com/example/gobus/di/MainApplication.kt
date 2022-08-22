package com.example.gobus.di

import android.app.Application
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

class MainApplication: Application() {
    companion object{
         lateinit var appComponent: AppComponent
    }
    private var mInstance: Application? = null
    fun getInstance(): Application{
        return mInstance!!
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this@MainApplication
        initDagger()
       // writeLogFile()
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .roomModule(RoomModule())
            .networkModule(NetworkModule())
            .build()
    }
    public fun writeLogFile(){

        if (Environment.isExternalStorageEmulated()) {
            val appDirectory = File(getExternalFilesDir("")?.toString() + "/gobus_log")

            val logDirectory = File("$appDirectory/log")
            val logFile = File(logDirectory, "logcat" + ".html")
            val logCrashFile = File(logDirectory, "logcat" + "_crashes.html")
            Log.d("FOLDER", logFile.toString())
            // create app folder
            if (!appDirectory.exists()) {
                appDirectory.mkdir()

            }

            // create log folder
            if (!logDirectory.exists()) {
                logDirectory.mkdir()
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Runtime.getRuntime().exec("logcat -c")
                Runtime.getRuntime().exec("logcat -f $logFile")
                Runtime.getRuntime().exec("logcat --buffer=crash -f $logCrashFile")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}