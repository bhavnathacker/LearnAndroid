package com.bhavnathacker.learnandroid

import android.app.Application
import android.content.Context

class LearnAndroidApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: LearnAndroidApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}