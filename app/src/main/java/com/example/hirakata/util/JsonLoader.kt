// File untuk ngeload data dari file json dari folder /raw /assets dll dengan gson
package com.example.hirakata.util

import android.content.Context
import com.example.hirakata.model.HiraganaItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonLoader {
    fun loadHiragana (context: Context, filename: String): List<HiraganaItem> {
        val json = context.assets.open(filename).bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<HiraganaItem>>() {}.type
        return Gson().fromJson(json, listType)
    }
}