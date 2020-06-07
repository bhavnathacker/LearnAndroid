package com.bhavnathacker.learnandroid.utils

import android.content.Context
import com.bhavnathacker.learnandroid.LearnAndroidApplication

object PrefUtils {

    const val PREF_NAME = "com.bhavnathacker.learnandroid.PREFERENCE_FILE_KEY"

    fun savePreference(key: String, value: String) {
        val context = LearnAndroidApplication.applicationContext()
        val sharedPref = context.getSharedPreferences(
            PREF_NAME, Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString(key, value)
            commit()
        }
    }

    fun getPreference(key: String, defaultValue: String): String? {
        val context = LearnAndroidApplication.applicationContext()
        val sharedPref = context.getSharedPreferences(
            PREF_NAME, Context.MODE_PRIVATE
        )
        return sharedPref.getString(key, defaultValue)
    }
}