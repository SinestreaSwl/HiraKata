package com.example.hirakata.util

import android.content.Context
import com.example.hirakata.model.QuizQuestion
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object QuizProvider {

    fun getRandomizedQuiz(context: Context, fileResId: Int, count: Int): List<QuizQuestion> {
        val inputStream = context.resources.openRawResource(fileResId)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val quizType = object : TypeToken<List<QuizQuestion>>() {}.type
        val fullList: List<QuizQuestion> = Gson().fromJson(jsonString, quizType)

        return fullList.shuffled().take(count)
    }
}
