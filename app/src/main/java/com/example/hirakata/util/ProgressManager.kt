// File yang digunakan untuk menyimpan status sesi yang telah dibuka
package com.example.hirakata.util

import android.content.Context

object ProgressManager {

    private const val PREF_NAME = "Progress"
    private const val KEY_MARKED_SET = "marked_set"

    // Fungsi Untuk Selalu Membuka Sesi Basic Secara Otomatis
    fun isUnlocked(context: Context, key: String): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val isBasic = key.endsWith("_basic")
        return prefs.getBoolean(key, isBasic)
    }

    // Fungsi Untuk Menyimpan Progress Sesi Apa Saja Yang Telah Terbuka
    fun unlock(context: Context, key: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(key, true).apply()
    }

    private fun getMarkedSet(context: Context): MutableSet<String> {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return prefs.getStringSet(KEY_MARKED_SET, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
    }

    fun isMarked(context: Context, kana: String): Boolean = getMarkedSet(context).contains(kana)

    fun mark(context: Context, kana: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val set = getMarkedSet(context)
        set.add(kana)
        prefs.edit().putStringSet(KEY_MARKED_SET, set).apply()
    }

    fun unmark(context: Context, kana: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val set = getMarkedSet(context)
        set.remove(kana)
        prefs.edit().putStringSet(KEY_MARKED_SET, set).apply()
    }

}