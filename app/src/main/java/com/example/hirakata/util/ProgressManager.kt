package com.example.hirakata.util

import android.content.Context
import android.content.SharedPreferences

object ProgressManager {

    private const val PREF_NAME = "Progress"

    fun isUnlocked(context: Context, key: String): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, key == "basic")
    }

    fun unlock(context: Context, key: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(key, true).apply()
    }

    fun resetAll(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}