package com.example.todolist

import android.app.Application
import android.content.Context

class Global : Application() {

    val context: Context? = null

    override fun onCreate() {
        super.onCreate()
        applicationContext
    }

    fun getAppContext(): Context? {
        return context
    }
}